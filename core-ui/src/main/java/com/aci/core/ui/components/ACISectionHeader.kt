package com.aci.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aci.core.ui.theme.ACISpacing
import com.aci.core.ui.theme.ACITheme

@Composable
fun ACISectionHeader(
    title: String,
    subtitle: String? = null,
    actionText: String? = null,
    onAction: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = ACISpacing.md, vertical = ACISpacing.sm),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        androidx.compose.foundation.layout.Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        if (actionText != null && onAction != null) {
            TextButton(onClick = onAction) {
                Text(
                    text = actionText,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Light Section")
@Composable
private fun ACISectionHeaderLightPreview() {
    ACITheme(useDarkTheme = false) {
        ACISectionHeader(
            title = "Upcoming Events",
            subtitle = "Events this week",
            actionText = "See All",
            onAction = {}
        )
    }
}

@Preview(showBackground = true, name = "Tamil Section")
@Composable
private fun ACISectionHeaderTamilPreview() {
    ACITheme(useDarkTheme = false) {
        ACISectionHeader(
            title = "வருகிற நிகழ்ச்சிகள்",
            subtitle = "இந்த வார நிகழ்ச்சிகள்",
            actionText = "அனைத்தையும் பார்",
            onAction = {}
        )
    }
}

@Preview(showBackground = true, name = "Dark Section")
@Composable
private fun ACISectionHeaderDarkPreview() {
    ACITheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            ACISectionHeader(
                title = "Upcoming Events",
                subtitle = "Events this week",
                actionText = "See All",
                onAction = {}
            )
        }
    }
}
