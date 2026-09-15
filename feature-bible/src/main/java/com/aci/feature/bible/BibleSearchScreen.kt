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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
fun BibleSearchScreen(
    query: String,
    onQueryChange: (String) -> Unit,
    results: List<BibleVerse>,
    booksById: Map<String, BibleBook>,
    language: Language,
    onVerseClick: (BibleVerse) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth().padding(ACISpacing.md)) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search the Bible… e.g. \"faith\"") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(ACISpacing.md))

        if (query.isBlank()) {
            ACIEmptyState(icon = Icons.Default.Search, title = "Search the Bible", subtitle = "Type a word or phrase to find matching verses.")
        } else if (results.isEmpty()) {
            ACIEmptyState(icon = Icons.Default.Search, title = "No matches", subtitle = "Try a different word.")
        } else {
            LazyColumn(contentPadding = PaddingValues(bottom = ACISpacing.xxl)) {
                items(results, key = { it.id }) { verse ->
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
    }
}
