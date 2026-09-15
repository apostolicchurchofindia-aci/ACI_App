package com.aci.feature.songs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aci.core.domain.model.Song
import com.aci.core.ui.components.ACIButton
import com.aci.core.ui.components.ACIButtonType
import com.aci.core.ui.components.ACICard
import com.aci.core.ui.components.ACIChip
import com.aci.core.ui.components.ACISectionHeader
import com.aci.core.ui.theme.ACISpacing
import com.aci.core.ui.theme.ACITheme
import kotlinx.datetime.Instant

@Composable
fun SongListScreen(
    songs: List<Song>,
    onSongClick: (Song) -> Unit,
    onRequestSong: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedLanguage by rememberSaveable { mutableStateOf("Telugu") }
    var query by rememberSaveable { mutableStateOf("") }
    var selectedCategory by rememberSaveable { mutableStateOf<String?>(null) }

    val categories = remember(songs) { songs.map { it.category }.filter { it.isNotBlank() }.distinct().sorted() }

    val filtered = remember(songs, query, selectedCategory, selectedLanguage) {
        val trimmedQuery = query.trim()
        songs.filter { song ->
            val matchesQuery = trimmedQuery.isBlank() || listOf(
                song.titleEn,
                song.titleTe.orEmpty(),
                song.titleTa,
                song.titleTeTranslit,
                song.titleTaTranslit,
                song.category,
                song.composer,
                song.artist,
                song.language,
                song.key?.name.orEmpty()
            ).any { it.contains(trimmedQuery, ignoreCase = true) }
            val matchesCategory = selectedCategory == null || song.category == selectedCategory
            val matchesLanguage = song.language.equals(selectedLanguage, ignoreCase = true)
            matchesQuery && matchesCategory && matchesLanguage
        }
    }

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(bottom = ACISpacing.xxl)
    ) {
        item {
            ACISectionHeader(
                title = "Song Library",
                subtitle = "${songs.size} songs · தமிழ் · English · తెలుగు"
            )
        }

        item {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ACISpacing.md),
                placeholder = { Text("Search songs...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(ACISpacing.sm))
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ACISpacing.md),
                horizontalArrangement = Arrangement.spacedBy(ACISpacing.xs)
            ) {
                ACIButton(
                    text = "Telugu",
                    onClick = { selectedLanguage = "Telugu" },
                    type = if (selectedLanguage == "Telugu") ACIButtonType.Primary else ACIButtonType.Outlined
                )
                ACIButton(
                    text = "Tamil",
                    onClick = { selectedLanguage = "Tamil" },
                    type = if (selectedLanguage == "Tamil") ACIButtonType.Primary else ACIButtonType.Outlined
                )
            }
            Spacer(modifier = Modifier.height(ACISpacing.sm))
        }

        item {
            ACIButton(
                text = "Request a Song",
                onClick = onRequestSong,
                type = ACIButtonType.Outlined,
                icon = Icons.Default.Add,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ACISpacing.md)
            )
            Spacer(modifier = Modifier.height(ACISpacing.sm))
        }

        if (categories.isNotEmpty()) {
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = ACISpacing.md),
                    horizontalArrangement = Arrangement.spacedBy(ACISpacing.xs)
                ) {
                    item {
                        ACIChip(
                            text = "All",
                            selected = selectedCategory == null,
                            onClick = { selectedCategory = null }
                        )
                    }
                    items(categories) { category ->
                        ACIChip(
                            text = category,
                            selected = selectedCategory == category,
                            onClick = { selectedCategory = if (selectedCategory == category) null else category }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(ACISpacing.sm))
            }
        }

        if (filtered.isEmpty()) {
            item {
                val message = if (query.isBlank() && selectedCategory == null) {
                    "No $selectedLanguage songs yet — check back soon."
                } else {
                    "No songs match your search."
                }
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(ACISpacing.md)
                )
            }
        }

        items(filtered, key = { it.id }) { song ->
            SongRow(song = song, onClick = { onSongClick(song) })
        }
    }
}

@Composable
private fun SongRow(song: Song, onClick: () -> Unit) {
    ACICard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ACISpacing.md, vertical = ACISpacing.xxs)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ACISpacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Icon(
                    imageVector = Icons.Default.MusicNote,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Column(modifier = Modifier.padding(start = ACISpacing.sm)) {
                    val nativeTitle = song.titleTe ?: song.titleTa.ifBlank { song.titleEn }
                    Text(
                        text = nativeTitle.ifBlank { song.titleEn },
                        style = MaterialTheme.typography.titleMedium
                    )
                    val translit = song.titleTeTranslit.ifBlank { song.titleTaTranslit }
                    if (translit.isNotBlank()) {
                        Text(
                            text = translit,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    } else if (nativeTitle != song.titleEn && song.titleEn.isNotBlank()) {
                        Text(
                            text = song.titleEn,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(ACISpacing.xxs),
                        modifier = Modifier.padding(top = ACISpacing.xxs)
                    ) {
                        song.key?.let { ACIChip(text = it.name, selected = false, onClick = {}) }
                        if (song.category.isNotBlank()) {
                            ACIChip(text = song.category, selected = false, onClick = {})
                        }
                    }
                }
            }
            Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null)
        }
    }
}

private val previewSongs = listOf(
    Song(
        id = "preview-1",
        titleEn = "Amazing Grace",
        titleTa = "அதிசய கிருபையே",
        language = "Multi",
        category = "Hymn",
        key = com.aci.core.domain.enum.SongKey.G,
        createdAt = Instant.DISTANT_PAST
    ),
    Song(
        id = "preview-2",
        titleEn = "He Is My Song",
        titleTe = "ఆయనే నా సంగీతము",
        titleTeTranslit = "Aayane Naa Sangeethamu",
        language = "Telugu",
        category = "Praise",
        createdAt = Instant.DISTANT_PAST
    )
)

@Preview(showBackground = true)
@Composable
private fun SongListScreenPreview() {
    ACITheme {
        SongListScreen(songs = previewSongs, onSongClick = {})
    }
}
