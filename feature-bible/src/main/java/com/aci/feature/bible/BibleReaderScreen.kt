package com.aci.feature.bible

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.aci.core.domain.model.forLanguage
import com.aci.core.domain.model.nameFor
import com.aci.core.ui.components.ACITopBar
import com.aci.core.ui.theme.ACISpacing
import kotlinx.coroutines.delay

@Composable
fun BibleReaderScreen(
    book: BibleBook,
    chapter: Int,
    verses: List<BibleVerse>,
    language: Language,
    bookmarkedVerseIds: Set<String>,
    fontScale: Float,
    onFontScaleToggle: () -> Unit,
    onToggleBookmark: (BibleVerse) -> Unit,
    onAddNote: (BibleVerse) -> Unit,
    onBack: () -> Unit,
    onPrevChapter: (() -> Unit)?,
    onNextChapter: (() -> Unit)?,
    onChapterRead: () -> Unit,
    modifier: Modifier = Modifier
) {
    val strings = bibleStringsFor(language)
    val speaker = rememberBibleSpeaker()
    val listState = rememberLazyListState()

    // Chapter counts as read once the member has plausibly been through it: either they reached
    // the last verse after a moment on the page, or they stayed long enough to have read it at a
    // steady pace. Listening to the whole chapter counts too (handled at the play call below).
    var dwellReached by remember(book.id, chapter) { mutableStateOf(false) }
    var reportedRead by remember(book.id, chapter) { mutableStateOf(false) }

    LaunchedEffect(book.id, chapter) {
        delay(MIN_DWELL_MILLIS)
        dwellReached = true
        delay(estimatedReadMillis(verses.size) - MIN_DWELL_MILLIS)
        if (!reportedRead) {
            reportedRead = true
            onChapterRead()
        }
    }

    val reachedLastVerse by remember(verses) {
        derivedStateOf {
            val last = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
            verses.isNotEmpty() && last >= verses.lastIndex
        }
    }

    LaunchedEffect(reachedLastVerse, dwellReached) {
        if (reachedLastVerse && dwellReached && !reportedRead) {
            reportedRead = true
            onChapterRead()
        }
    }

    Column(modifier = modifier.fillMaxWidth()) {
        ACITopBar(
            title = "${book.nameFor(language)} $chapter",
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
                            ) {
                                if (!reportedRead) {
                                    reportedRead = true
                                    onChapterRead()
                                }
                            }
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
            modifier = Modifier.fillMaxWidth().weight(1f),
            contentPadding = PaddingValues(ACISpacing.lg)
        ) {
            itemsIndexed(verses, key = { _, verse -> verse.id }) { index, verse ->
                VerseRow(
                    verse = verse,
                    language = language,
                    isBeingSpoken = index == speaker.speakingIndex,
                    isBookmarked = bookmarkedVerseIds.contains(verse.id),
                    fontScale = fontScale,
                    onToggleBookmark = { onToggleBookmark(verse) },
                    onAddNote = { onAddNote(verse) }
                )
                Spacer(modifier = Modifier.height(ACISpacing.sm))
            }
        }

        HorizontalDivider()
        Row(
            modifier = Modifier.fillMaxWidth().padding(ACISpacing.md),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = { onPrevChapter?.invoke() }, enabled = onPrevChapter != null) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                Spacer(modifier = Modifier.width(ACISpacing.xxs))
                Text(strings.previous)
            }
            TextButton(onClick = { onNextChapter?.invoke() }, enabled = onNextChapter != null) {
                Text(strings.next)
                Spacer(modifier = Modifier.width(ACISpacing.xxs))
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
            }
        }
    }
}

@Composable
private fun VerseRow(
    verse: BibleVerse,
    language: Language,
    isBeingSpoken: Boolean,
    isBookmarked: Boolean,
    fontScale: Float,
    onToggleBookmark: () -> Unit,
    onAddNote: () -> Unit
) {
    Row(verticalAlignment = Alignment.Top) {
        Text(
            text = "${verse.verseNumber} ",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = verse.text.forLanguage(language),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 17.sp * fontScale,
                    lineHeight = 26.sp * fontScale
                ),
                color = if (isBeingSpoken) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
            )
        }
        IconButton(onClick = onAddNote, modifier = Modifier.size(36.dp)) {
            Icon(
                imageVector = Icons.Default.EditNote,
                contentDescription = "Add note on verse ${verse.verseNumber}",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(20.dp)
            )
        }
        IconButton(onClick = onToggleBookmark, modifier = Modifier.size(36.dp)) {
            Icon(
                imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                contentDescription = "Bookmark verse ${verse.verseNumber}",
                tint = if (isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
