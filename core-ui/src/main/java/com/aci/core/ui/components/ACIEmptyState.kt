package com.aci.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aci.core.ui.theme.ACISpacing
import com.aci.core.ui.theme.ACITheme

@Composable
fun ACIEmptyState(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    cta: String? = null,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(ACISpacing.xl),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier.size(96.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(ACISpacing.lg))
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
        if (subtitle != null) {
            Spacer(modifier = Modifier.height(ACISpacing.xs))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        if (cta != null && onClick != null) {
            Spacer(modifier = Modifier.height(ACISpacing.lg))
            ACIButton(
                text = cta,
                onClick = onClick,
                type = ACIButtonType.Primary
            )
        }
    }
}

@Preview(showBackground = true, name = "Light Empty")
@Composable
private fun ACIEmptyStateLightPreview() {
    ACITheme(useDarkTheme = false) {
        ACIEmptyState(
            icon = Icons.Default.Inbox,
            title = "No Items",
            subtitle = "There are no items to display right now.",
            cta = "Refresh",
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Tamil Empty")
@Composable
private fun ACIEmptyStateTamilPreview() {
    ACITheme(useDarkTheme = false) {
        ACIEmptyState(
            icon = Icons.Default.Inbox,
            title = "பொருட்கள் இல்லை",
            subtitle = "தற்போது காண்பிக்க பொருட்கள் எதுவும் இல்லை.",
            cta = "புதுப்பி",
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Dark Empty")
@Composable
private fun ACIEmptyStateDarkPreview() {
    ACITheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            ACIEmptyState(
                icon = Icons.Default.Inbox,
                title = "No Items",
                subtitle = "There are no items to display right now.",
                cta = "Refresh",
                onClick = {}
            )
        }
    }
}
