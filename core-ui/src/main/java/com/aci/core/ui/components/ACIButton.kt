package com.aci.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aci.core.ui.theme.ACIShapes
import com.aci.core.ui.theme.ACISpacing
import com.aci.core.ui.theme.ACITheme

enum class ACIButtonType { Primary, Secondary, Outlined, Text }

@Composable
fun ACIButton(
    text: String,
    onClick: () -> Unit,
    type: ACIButtonType = ACIButtonType.Primary,
    enabled: Boolean = true,
    icon: ImageVector? = null,
    modifier: Modifier = Modifier
) {
    val buttonContent: @Composable androidx.compose.foundation.layout.RowScope.() -> Unit = {
        if (icon != null) {
            Icon(imageVector = icon, contentDescription = null)
            Spacer(modifier = Modifier.width(ACISpacing.xs))
        }
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
    }

    when (type) {
        ACIButtonType.Primary -> {
            Button(
                onClick = onClick,
                enabled = enabled,
                modifier = modifier,
                shape = ACIShapes.roundedLarge,
                content = buttonContent
            )
        }
        ACIButtonType.Secondary -> {
            Button(
                onClick = onClick,
                enabled = enabled,
                modifier = modifier,
                shape = ACIShapes.roundedLarge,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                content = buttonContent
            )
        }
        ACIButtonType.Outlined -> {
            OutlinedButton(
                onClick = onClick,
                enabled = enabled,
                modifier = modifier,
                shape = ACIShapes.roundedLarge,
                content = buttonContent
            )
        }
        ACIButtonType.Text -> {
            TextButton(
                onClick = onClick,
                enabled = enabled,
                modifier = modifier,
                content = buttonContent
            )
        }
    }
}

@Preview(showBackground = true, name = "Light Buttons")
@Composable
private fun ACIButtonLightPreview() {
    ACITheme(useDarkTheme = false) {
        Column(
            modifier = Modifier.padding(ACISpacing.md),
            verticalArrangement = Arrangement.spacedBy(ACISpacing.sm)
        ) {
            ACIButton(text = "Primary Button", onClick = {}, type = ACIButtonType.Primary)
            ACIButton(text = "Secondary Button", onClick = {}, type = ACIButtonType.Secondary)
            ACIButton(text = "Outlined Button", onClick = {}, type = ACIButtonType.Outlined)
            ACIButton(text = "Text Button", onClick = {}, type = ACIButtonType.Text)
            ACIButton(text = "Disabled", onClick = {}, enabled = false)
        }
    }
}

@Preview(showBackground = true, name = "Tamil Buttons")
@Composable
private fun ACIButtonTamilPreview() {
    ACITheme(useDarkTheme = false) {
        Column(
            modifier = Modifier.padding(ACISpacing.md),
            verticalArrangement = Arrangement.spacedBy(ACISpacing.sm)
        ) {
            ACIButton(text = "பதிவு செய்", onClick = {}, type = ACIButtonType.Primary)
            ACIButton(text = "கிரியை ரத்து", onClick = {}, type = ACIButtonType.Outlined)
        }
    }
}

@Preview(showBackground = true, name = "Dark Buttons")
@Composable
private fun ACIButtonDarkPreview() {
    ACITheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier.padding(ACISpacing.md),
                verticalArrangement = Arrangement.spacedBy(ACISpacing.sm)
            ) {
                ACIButton(text = "Primary Button", onClick = {}, type = ACIButtonType.Primary)
                ACIButton(text = "Secondary Button", onClick = {}, type = ACIButtonType.Secondary)
                ACIButton(text = "Outlined Button", onClick = {}, type = ACIButtonType.Outlined)
                ACIButton(text = "Text Button", onClick = {}, type = ACIButtonType.Text)
            }
        }
    }
}
