package com.aci.feature.songs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.aci.core.domain.model.Song
import com.aci.core.domain.model.SongLyricSection
import com.aci.core.ui.components.ACIButton
import com.aci.core.ui.components.ACIButtonType
import com.aci.core.ui.components.ACIChip
import com.aci.core.ui.components.ACITopBar
import com.aci.core.ui.theme.ACISpacing
import com.aci.core.ui.theme.ACITheme
import kotlinx.datetime.Instant

/**
 * Lyrics layout inspired by ChristianLyricz-style songbook pages: native-script title with a
 * transliteration underneath, sections labeled (Chorus/Verse), and a songbook attribution footer.
 */
@Composable
fun SongDetailScreen(
    song: Song,
    lyricSections: List<SongLyricSection>,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isFavorite by remember { mutableStateOf(false) }
    var fontScale by remember { mutableFloatStateOf(1f) }
    var selectedLanguage by remember { mutableStateOf(LyricsLanguage.NATIVE) }

    Column(modifier = modifier.fillMaxWidth()) {
        ACITopBar(
            title = song.titleEn.ifBlank { song.titleTe.orEmpty() },
            onNavClick = onBack,
            actions = {
                IconButton(onClick = { fontScale = if (fontScale >= 1.4f) 1f else fontScale + 0.2f }) {
                    Icon(Icons.Default.TextFields, contentDescription = "Increase font size")
                }
                IconButton(onClick = { isFavorite = !isFavorite }) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                    )
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Share, contentDescription = "Share")
                }
            }
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(ACISpacing.lg)
        ) {
            item {
                SongHeader(song = song)
                Spacer(modifier = Modifier.height(ACISpacing.lg))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(ACISpacing.md))
                val hasTranslit = lyricSections.any { it.translitLyrics.isNotBlank() }
                val hasTamil = lyricSections.any { it.translationTa.isNotBlank() }
                if (hasTranslit || hasTamil) {
                    val nativeLabel = when (lyricSections.firstOrNull { it.translitLyrics.isNotBlank() }?.language) {
                        com.aci.core.domain.enum.Language.TELUGU -> "Telugu"
                        com.aci.core.domain.enum.Language.TAMIL -> "Tamil"
                        else -> "Native"
                    }
                    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                        Row(horizontalArrangement = Arrangement.spacedBy(ACISpacing.xs)) {
                            ACIButton(
                                text = nativeLabel,
                                onClick = { selectedLanguage = LyricsLanguage.NATIVE },
                                type = if (selectedLanguage == LyricsLanguage.NATIVE) ACIButtonType.Primary else ACIButtonType.Outlined
                            )
                            if (hasTranslit) {
                                ACIButton(
                                    text = "English",
                                    onClick = { selectedLanguage = LyricsLanguage.ENGLISH },
                                    type = if (selectedLanguage == LyricsLanguage.ENGLISH) ACIButtonType.Primary else ACIButtonType.Outlined
                                )
                            }
                            if (hasTamil) {
                                ACIButton(
                                    text = "Tamil",
                                    onClick = { selectedLanguage = LyricsLanguage.TAMIL },
                                    type = if (selectedLanguage == LyricsLanguage.TAMIL) ACIButtonType.Primary else ACIButtonType.Outlined
                                )
                            }
                        }
                    }
                    if (selectedLanguage == LyricsLanguage.TAMIL) {
                        Spacer(modifier = Modifier.height(ACISpacing.xxs))
                        Text(
                            text = "Transliterated to Tamil script — not an official ACI source",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Spacer(modifier = Modifier.height(ACISpacing.md))
                }
            }

            items(lyricSections.sortedBy { it.orderIndex }, key = { it.id }) { section ->
                LyricSectionBlock(section = section, fontScale = fontScale, selectedLanguage = selectedLanguage)
                Spacer(modifier = Modifier.height(ACISpacing.lg))
            }

            item {
                SongbookFooter(song = song)
            }
        }
    }
}

@Composable
private fun SongHeader(song: Song) {
    val nativeTitle = song.titleTe ?: song.titleTa.ifBlank { null }
    val translit = song.titleTeTranslit.ifBlank { song.titleTaTranslit }

    Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Text(
            text = nativeTitle ?: song.titleEn,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        if (translit.isNotBlank()) {
            Spacer(modifier = Modifier.height(ACISpacing.xxs))
            Text(
                text = translit,
                style = MaterialTheme.typography.titleMedium.copy(fontStyle = FontStyle.Italic),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
        if (nativeTitle != null && song.titleEn.isNotBlank() && song.titleEn != nativeTitle) {
            Spacer(modifier = Modifier.height(ACISpacing.xxs))
            Text(
                text = song.titleEn,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(ACISpacing.sm))
        Row(horizontalArrangement = Arrangement.spacedBy(ACISpacing.xs)) {
            if (song.category.isNotBlank()) ACIChip(text = song.category, onClick = {})
            song.key?.let { ACIChip(text = "Key: ${it.name}", onClick = {}) }
            song.bpm?.let { ACIChip(text = "$it BPM", onClick = {}) }
        }

        Spacer(modifier = Modifier.height(ACISpacing.md))
        Row(horizontalArrangement = Arrangement.spacedBy(ACISpacing.sm)) {
            ACIButton(text = "Practice", onClick = {}, type = ACIButtonType.Primary)
            ACIButton(text = "Stage Mode", onClick = {}, type = ACIButtonType.Outlined, icon = Icons.AutoMirrored.Filled.MenuBook)
        }
    }
}

private enum class LyricsLanguage { NATIVE, ENGLISH, TAMIL }

@Composable
private fun LyricSectionBlock(section: SongLyricSection, fontScale: Float, selectedLanguage: LyricsLanguage) {
    val text = when (selectedLanguage) {
        LyricsLanguage.ENGLISH -> section.translitLyrics.ifBlank { section.lyrics }
        LyricsLanguage.TAMIL -> section.translationTa.ifBlank { section.lyrics }
        LyricsLanguage.NATIVE -> section.lyrics
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = section.sectionLabel.uppercase(),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(ACISpacing.xs))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp * fontScale, lineHeight = 28.sp * fontScale)
        )
    }
}

@Composable
private fun SongbookFooter(song: Song) {
    if (song.songbookName.isBlank()) return
    Column(modifier = Modifier.fillMaxWidth().padding(top = ACISpacing.md)) {
        HorizontalDivider()
        Spacer(modifier = Modifier.height(ACISpacing.sm))
        Text(
            text = "In these songbooks",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(ACISpacing.xs))
        val number = song.songbookNumber?.let { " #$it" }.orEmpty()
        ACIChip(text = "${song.songbookName}$number", onClick = {})
    }
}

private val previewSong = Song(
    id = "preview-aayane",
    titleEn = "He Is My Song",
    titleTe = "ఆయనే నా సంగీతము",
    titleTeTranslit = "Aayane Naa Sangeethamu",
    language = "Telugu",
    category = "Praise",
    songbookName = "Joyful Journey",
    songbookNumber = 121,
    createdAt = Instant.DISTANT_PAST
)

private val previewLyrics = listOf(
    SongLyricSection(
        id = "preview-chorus",
        songId = previewSong.id,
        language = com.aci.core.domain.enum.Language.TELUGU,
        sectionLabel = "Pallavi",
        lyrics = "అందాల ఆశాకిరణం - డెందాల చీకటి బాపెన్",
        translitLyrics = "Aayane naa sangeethamu balamaina kotayanu",
        orderIndex = 0
    )
)

@Preview(showBackground = true, name = "Song Detail — Telugu")
@Composable
private fun SongDetailScreenPreview() {
    ACITheme {
        SongDetailScreen(
            song = previewSong,
            lyricSections = previewLyrics,
            onBack = {}
        )
    }
}
