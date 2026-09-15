package com.aci.feature.bible

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.aci.core.domain.enum.Language
import com.aci.core.domain.enum.Testament
import com.aci.core.domain.model.BibleBook
import com.aci.core.domain.model.nameFor
import com.aci.core.domain.model.translationCodeFor
import com.aci.core.ui.components.ACICard
import com.aci.core.ui.components.ACISectionHeader
import com.aci.core.ui.theme.ACISpacing

@Composable
fun BibleBooksScreen(
    books: List<BibleBook>,
    language: Language,
    onBookClick: (BibleBook) -> Unit,
    onSearchClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onReadingPlanClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val strings = bibleStringsFor(language)
    val oldTestament = books.filter { it.testament == Testament.OLD }
    val newTestament = books.filter { it.testament == Testament.NEW }

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(bottom = ACISpacing.xxl)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ACISpacing.md, vertical = ACISpacing.sm),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "${strings.holyBible} — ${translationCodeFor(language)}",
                    style = MaterialTheme.typography.titleLarge
                )
                Row {
                    IconButton(onClick = onSearchClick) {
                        Icon(Icons.Default.Search, contentDescription = strings.searchTheBible)
                    }
                }
            }
        }

        item {
            ACICard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ACISpacing.md, vertical = ACISpacing.xxs)
                    .clickable(onClick = onBookmarksClick)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(ACISpacing.md),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(strings.bookmarksAndNotes, style = MaterialTheme.typography.titleMedium)
                    Icon(Icons.Default.ChevronRight, contentDescription = null)
                }
            }
        }

        item {
            ACICard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ACISpacing.md, vertical = ACISpacing.xxs)
                    .clickable(onClick = onReadingPlanClick)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(ACISpacing.md),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(strings.readingPlanTitle, style = MaterialTheme.typography.titleMedium)
                    Icon(Icons.Default.ChevronRight, contentDescription = null)
                }
            }
        }

        item { ACISectionHeader(title = strings.oldTestament, subtitle = strings.booksCount(oldTestament.size)) }
        items(oldTestament, key = { it.id }) { book -> BookRow(book, language, strings, onClick = { onBookClick(book) }) }

        item { ACISectionHeader(title = strings.newTestament, subtitle = strings.booksCount(newTestament.size)) }
        items(newTestament, key = { it.id }) { book -> BookRow(book, language, strings, onClick = { onBookClick(book) }) }
    }
}

@Composable
private fun BookRow(book: BibleBook, language: Language, strings: BibleStrings, onClick: () -> Unit) {
    ACICard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ACISpacing.md, vertical = ACISpacing.xxs)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(ACISpacing.md),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(book.nameFor(language), style = MaterialTheme.typography.titleMedium)
                Text(
                    strings.chaptersCount(book.numChapters),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null)
        }
    }
}
