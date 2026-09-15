package com.aci.feature.bible

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.aci.core.domain.enum.Language
import com.aci.core.domain.model.BibleBook
import com.aci.core.domain.model.nameFor
import com.aci.core.ui.components.ACISectionHeader
import com.aci.core.ui.theme.ACIShapes
import com.aci.core.ui.theme.ACISpacing

@Composable
fun BibleChapterPickerScreen(
    book: BibleBook,
    language: Language,
    onChapterClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(ACISpacing.md),
        horizontalArrangement = Arrangement.spacedBy(ACISpacing.sm),
        verticalArrangement = Arrangement.spacedBy(ACISpacing.sm)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            ACISectionHeader(title = book.nameFor(language), subtitle = bibleStringsFor(language).selectAChapter)
        }
        items(book.numChapters) { index ->
            val chapter = index + 1
            Surface(
                modifier = Modifier.clickable { onChapterClick(chapter) },
                shape = ACIShapes.roundedSmall,
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Text(
                    text = chapter.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = ACISpacing.md)
                )
            }
        }
    }
}
