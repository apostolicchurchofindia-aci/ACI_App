package com.aci.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aci.core.ui.theme.ACIShapes
import com.aci.core.ui.theme.ACISpacing
import com.aci.core.ui.theme.ACITheme

@Composable
fun ACIConfirmationDialog(
    title: String,
    message: String,
    confirmText: String,
    dismissText: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    showDismiss: Boolean = true,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ACISpacing.sm, vertical = ACISpacing.md),
                horizontalArrangement = Arrangement.End
            ) {
                if (showDismiss) {
                    ACIButton(
                        text = dismissText,
                        onClick = onDismiss,
                        type = ACIButtonType.Text
                    )
                    Spacer(modifier = Modifier.width(ACISpacing.sm))
                }
                ACIButton(
                    text = confirmText,
                    onClick = onConfirm,
                    type = ACIButtonType.Primary
                )
            }
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        shape = ACIShapes.roundedLarge,
        containerColor = MaterialTheme.colorScheme.surface,
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        textContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier
    )
}

@Preview(showBackground = true, name = "Light Dialog")
@Composable
private fun ACIConfirmationDialogLightPreview() {
    ACITheme(useDarkTheme = false) {
        ACIConfirmationDialog(
            title = "Confirm Action",
            message = "Are you sure you want to proceed with this action?",
            confirmText = "Confirm",
            dismissText = "Cancel",
            onConfirm = {},
            onDismiss = {}
        )
    }
}

@Preview(showBackground = true, name = "Tamil Dialog")
@Composable
private fun ACIConfirmationDialogTamilPreview() {
    ACITheme(useDarkTheme = false) {
        ACIConfirmationDialog(
            title = "செயலை உறுதிப்படுத்து",
            message = "இந்த செயலைத் தொடர வேண்டுமா?",
            confirmText = "உறுதிப்படுத்து",
            dismissText = "ரத்து",
            onConfirm = {},
            onDismiss = {}
        )
    }
}

@Preview(showBackground = true, name = "Dark Dialog")
@Composable
private fun ACIConfirmationDialogDarkPreview() {
    ACITheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            ACIConfirmationDialog(
                title = "Confirm Action",
                message = "Are you sure you want to proceed with this action?",
                confirmText = "Confirm",
                dismissText = "Cancel",
                onConfirm = {},
                onDismiss = {}
            )
        }
    }
}
