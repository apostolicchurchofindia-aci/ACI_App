package com.aci.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import com.aci.core.ui.theme.ACISpacing
import com.aci.core.ui.theme.ACITheme

@Composable
fun ACIChip(
    text: String,
    selected: Boolean = false,
    onClick: () -> Unit = {},
    leadingIcon: ImageVector? = null,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = {
            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (leadingIcon != null) {
                    Icon(imageVector = leadingIcon, contentDescription = null)
                    Spacer(modifier = Modifier.width(ACISpacing.xxs))
                }
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        },
        modifier = modifier,
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
            selectedLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = selected,
            borderColor = MaterialTheme.colorScheme.outline
        )
    )
}

@Preview(showBackground = true, name = "Light Chips")
@Composable
private fun ACIChipLightPreview() {
    ACITheme(useDarkTheme = false) {
        Row(
            modifier = Modifier.padding(ACISpacing.md),
            horizontalArrangement = Arrangement.spacedBy(ACISpacing.xs)
        ) {
            ACIChip(text = "All", selected = true, onClick = {})
            ACIChip(text = "Songs", selected = false, onClick = {})
            ACIChip(text = "Sermons", selected = false, leadingIcon = Icons.Default.Favorite, onClick = {})
        }
    }
}

@Preview(showBackground = true, name = "Tamil Chips")
@Composable
private fun ACIChipTamilPreview() {
    ACITheme(useDarkTheme = false) {
        Row(
            modifier = Modifier.padding(ACISpacing.md),
            horizontalArrangement = Arrangement.spacedBy(ACISpacing.xs)
        ) {
            ACIChip(text = "அனைத்தும்", selected = true, onClick = {})
            ACIChip(text = "பாடல்கள்", selected = false, onClick = {})
        }
    }
}

@Preview(showBackground = true, name = "Dark Chips")
@Composable
private fun ACIChipDarkPreview() {
    ACITheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            Row(
                modifier = Modifier.padding(ACISpacing.md),
                horizontalArrangement = Arrangement.spacedBy(ACISpacing.xs)
            ) {
                ACIChip(text = "All", selected = true, onClick = {})
                ACIChip(text = "Songs", selected = false, onClick = {})
                ACIChip(text = "Sermons", selected = false, leadingIcon = Icons.Default.Favorite, onClick = {})
            }
        }
    }
}
