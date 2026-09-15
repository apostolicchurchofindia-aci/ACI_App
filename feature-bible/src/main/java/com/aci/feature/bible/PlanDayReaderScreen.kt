package com.aci.feature.bible

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aci.core.domain.enum.Language
import com.aci.core.domain.model.BibleBook
import com.aci.core.domain.model.BibleVerse
import com.aci.core.domain.model.ReadingPlanDay
import com.aci.core.domain.model.forLanguage
import com.aci.core.domain.model.label
import com.aci.core.domain.model.nameFor
import com.aci.core.ui.components.ACITopBar
import com.aci.core.ui.theme.ACISpacing
import kotlinx.coroutines.delay

/**
 * Reads one reading-plan day and nothing else — the day's chapters run together in a single
 * scroll, with no chapter navigation, so the member sees exactly what the plan asked of them.
 *
 * Progress is still per chapter: each one is marked read as its final verse scrolls into view,
 * so a day covering three chapters can be partly done.
 */
@Composable
fun PlanDayReaderScreen(
    day: ReadingPlanDay,
    verses: List<BibleVerse>,
    booksById: Map<String, BibleBook>,
    language: Language,
    bookmarkedVerseIds: Set<String>,
    fontScale: Float,
    onFontScaleToggle: () -> Unit,
    onToggleBookmark: (BibleVerse) -> Unit,
    onAddNote: (BibleVerse) -> Unit,
    onChapterRead: (String, Int) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val strings = bibleStringsFor(language)
    val speaker = rememberBibleSpeaker()
    val listState = rememberLazyListState()

    // Chapters in plan order, each with its own verses, so headings sit with their text.
    val chapterGroups = remember(verses) {
        verses.groupBy { it.bookId to it.chapter }.toList()
    }

    // Verse id that ends each chapter — seeing it is what marks that chapter read.
    val lastVerseIdPerChapter = remember(verses) {
        verses.groupBy { it.bookId to it.chapter }
            .mapNotNull { (key, chapterVerses) -> chapterVerses.lastOrNull()?.id?.let { it to key } }
            .toMap()
    }

    var dwellReached by remember(day.dayNumber) { mutableStateOf(false) }
    val reported = remember(day.dayNumber) { mutableStateOf(setOf<Pair<String, Int>>()) }

    fun report(chapter: Pair<String, Int>) {
        if (chapter in reported.value) return
        reported.value = reported.value + chapter
        onChapterRead(chapter.first, chapter.second)
    }

    fun reportAll() = lastVerseIdPerChapter.values.forEach(::report)

    LaunchedEffect(day.dayNumber, verses.size) {
        if (verses.isEmpty()) return@LaunchedEffect
        delay(MIN_DWELL_MILLIS)
        dwellReached = true
        delay(estimatedReadMillis(verses.size) - MIN_DWELL_MILLIS)
        reportAll()
    }

    val visibleChapterEnds by remember(lastVerseIdPerChapter) {
        derivedStateOf {
            listState.layoutInfo.visibleItemsInfo
                .mapNotNull { lastVerseIdPerChapter[it.key as? String] }
        }
    }

    LaunchedEffect(visibleChapterEnds, dwellReached) {
        if (dwellReached) visibleChapterEnds.forEach(::report)
    }

    Column(modifier = modifier.fillMaxWidth()) {
        ACITopBar(
            title = day.label(booksById, language),
            onNavClick = onBack,
            actions = {
                IconButton(
                    onClick = {
                        if (speaker.isSpeaking) {
                            speaker.stop()
                        } else {
                            speaker.speak(
                                verses = verses.map { it.text.forLanguage(language) },
                                language = language
                            ) { reportAll() }
                        }
                    },
                    enabled = speaker.isReady && verses.isNotEmpty()
                ) {
                    Icon(
                        imageVector = if (speaker.isSpeaking) Icons.Default.Stop else Icons.Default.VolumeUp,
                        contentDescription = if (speaker.isSpeaking) strings.stopAudio else strings.playAudio
                    )
                }
                IconButton(onClick = onFontScaleToggle) {
                    Icon(Icons.Default.TextFields, contentDescription = strings.fontSize)
                }
            }
        )

        if (!speaker.isLanguageAvailable) {
            Text(
                text = strings.audioUnavailable,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(horizontal = ACISpacing.lg, vertical = ACISpacing.xs)
            )
        }

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(ACISpacing.lg)
        ) {
            chapterGroups.forEach { (chapter, chapterVerses) ->
                item(key = "heading-${chapter.first}-${chapter.second}") {
                    Text(
                        text = "${booksById[chapter.first]?.nameFor(language) ?: chapter.first} ${chapter.second}",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = ACISpacing.md, bottom = ACISpacing.xs)
                    )
                }

                items(chapterVerses, key = { it.id }) { verse ->
                    PlanVerseRow(
                        verse = verse,
                        language = language,
                        fontScale = fontScale,
                        isBookmarked = verse.id in bookmarkedVerseIds,
                        onToggleBookmark = { onToggleBookmark(verse) },
                        onAddNote = { onAddNote(verse) }
                    )
                    Spacer(modifier = Modifier.height(ACISpacing.sm))
                }
            }
        }
    }
}

@Composable
private fun PlanVerseRow(
    verse: BibleVerse,
    language: Language,
    fontScale: Float,
    isBookmarked: Boolean,
    onToggleBookmark: () -> Unit,
    onAddNote: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "${verse.verseNumber} ",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = verse.text.forLanguage(language),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 17.sp * fontScale,
                lineHeight = 26.sp * fontScale
            ),
            modifier = Modifier.weight(1f)
        )
        // Compact buttons: full-size ones cost ~96dp of width, which cramps the verse into a
        // narrow column — especially in Tamil and Telugu, where words are longer.
        VerseActionButton(
            icon = Icons.Default.EditNote,
            contentDescription = "Add note on verse ${verse.verseNumber}",
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            onClick = onAddNote
        )
        VerseActionButton(
            icon = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
            contentDescription = "Bookmark verse ${verse.verseNumber}",
            tint = if (isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
            onClick = onToggleBookmark
        )
    }
}

@Composable
private fun VerseActionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentDescription: String,
    tint: androidx.compose.ui.graphics.Color,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick, modifier = Modifier.size(36.dp)) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier.size(20.dp)
        )
    }
}
