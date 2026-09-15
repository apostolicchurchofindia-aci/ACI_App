package com.aci.feature.bible

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aci.core.domain.enum.Language
import com.aci.core.domain.model.BibleBook
import com.aci.core.domain.model.BibleVerse
import com.aci.core.domain.model.forLanguage
import com.aci.core.domain.model.nameFor
import com.aci.core.ui.components.ACICard
import com.aci.core.ui.components.ACIEmptyState
import com.aci.core.ui.theme.ACISpacing

@Composable
fun BibleBookmarksScreen(
    bookmarkedVerses: List<BibleVerse>,
    booksById: Map<String, BibleBook>,
    language: Language,
    onVerseClick: (BibleVerse) -> Unit,
    modifier: Modifier = Modifier
) {
    if (bookmarkedVerses.isEmpty()) {
        ACIEmptyState(
            icon = Icons.Default.Bookmark,
            title = "No bookmarks yet",
            subtitle = "Tap the bookmark icon on any verse while reading to save it here."
        )
        return
    }

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(ACISpacing.md)
    ) {
        items(bookmarkedVerses, key = { it.id }) { verse ->
            val book = booksById[verse.bookId]
            ACICard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = ACISpacing.xxs)
                    .clickable { onVerseClick(verse) }
            ) {
                Column(modifier = Modifier.fillMaxWidth().padding(ACISpacing.md)) {
                    Text(verse.text.forLanguage(language), style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(ACISpacing.xxs))
                    Text(
                        "${book?.nameFor(language) ?: verse.bookId} ${verse.chapter}:${verse.verseNumber}",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
