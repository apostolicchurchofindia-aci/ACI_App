package com.aci.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aci.core.ui.theme.ACIShapes
import com.aci.core.ui.theme.ACISpacing
import com.aci.core.ui.theme.ACITheme

@Composable
fun ACICard(
    modifier: Modifier = Modifier,
    elevation: androidx.compose.foundation.layout.WindowInsets? = null,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = ACIShapes.roundedLarge,
        color = MaterialTheme.colorScheme.surface,
        contentColor = contentColor,
        tonalElevation = 2.dp,
        shadowElevation = 2.dp,
        content = content
    )
}

@Preview(showBackground = true, name = "Light Card")
@Composable
private fun ACICardLightPreview() {
    ACITheme(useDarkTheme = false) {
        ACICard(
            modifier = Modifier.padding(ACISpacing.md)
        ) {
            Column(modifier = Modifier.padding(ACISpacing.md)) {
                Text("Hello Card", style = MaterialTheme.typography.titleLarge)
                Text("This is sample content inside a card.", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true, name = "Dark Card")
@Composable
private fun ACICardDarkPreview() {
    ACITheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            ACICard(
                modifier = Modifier.padding(ACISpacing.md)
            ) {
                Column(modifier = Modifier.padding(ACISpacing.md)) {
                    Text("வணக்கம் Card", style = MaterialTheme.typography.titleLarge)
                    Text("This is dark mode content.", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
