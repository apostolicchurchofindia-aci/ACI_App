package com.aci.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aci.core.ui.theme.ACISpacing
import com.aci.core.ui.theme.ACITheme

@Composable
fun ACIErrorState(
    message: String,
    onRetry: (() -> Unit)? = null,
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
            imageVector = Icons.Default.ErrorOutline,
            contentDescription = "Error",
            modifier = Modifier.size(96.dp),
            tint = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(ACISpacing.lg))
        Text(
            text = "Something went wrong",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(ACISpacing.xs))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        if (onRetry != null) {
            Spacer(modifier = Modifier.height(ACISpacing.lg))
            ACIButton(
                text = "Retry",
                onClick = onRetry,
                type = ACIButtonType.Primary
            )
        }
    }
}

@Preview(showBackground = true, name = "Light Error")
@Composable
private fun ACIErrorStateLightPreview() {
    ACITheme(useDarkTheme = false) {
        ACIErrorState(
            message = "Unable to load data. Please check your connection.",
            onRetry = {}
        )
    }
}

@Preview(showBackground = true, name = "Tamil Error")
@Composable
private fun ACIErrorStateTamilPreview() {
    ACITheme(useDarkTheme = false) {
        ACIErrorState(
            message = "தரவு ஏற்ற முடியவில்லை. இணைப்பு சரிபார்க்கவும்.",
            onRetry = {}
        )
    }
}

@Preview(showBackground = true, name = "Dark Error")
@Composable
private fun ACIErrorStateDarkPreview() {
    ACITheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            ACIErrorState(
                message = "Unable to load data. Please check your connection.",
                onRetry = {}
            )
        }
    }
}
