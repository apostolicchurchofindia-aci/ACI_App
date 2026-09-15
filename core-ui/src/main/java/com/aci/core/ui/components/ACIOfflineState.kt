package com.aci.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
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
fun ACIOfflineState(
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
            imageVector = Icons.Default.WifiOff,
            contentDescription = "Offline",
            modifier = Modifier.size(96.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(ACISpacing.lg))
        Text(
            text = "No Internet Connection",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(ACISpacing.xs))
        Text(
            text = "Please check your internet settings and try again.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        if (onRetry != null) {
            Spacer(modifier = Modifier.height(ACISpacing.lg))
            ACIButton(
                text = "Try Again",
                onClick = onRetry,
                type = ACIButtonType.Primary
            )
        }
    }
}

@Preview(showBackground = true, name = "Light Offline")
@Composable
private fun ACIOfflineStateLightPreview() {
    ACITheme(useDarkTheme = false) {
        ACIOfflineState(onRetry = {})
    }
}

@Preview(showBackground = true, name = "Tamil Offline")
@Composable
private fun ACIOfflineStateTamilPreview() {
    ACITheme(useDarkTheme = false) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ACISpacing.xl),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.WifiOff,
                contentDescription = "Offline",
                modifier = Modifier.size(96.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(ACISpacing.lg))
            Text(
                text = "இணைய இணைப்பு இல்லை",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(ACISpacing.xs))
            Text(
                text = "உங்கள் இணைய அமைப்புகளை சரிபார்த்து மீண்டும் முயற்சிக்கவும்.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(ACISpacing.lg))
            ACIButton(
                text = "மீண்டும் முயற்சி",
                onClick = {},
                type = ACIButtonType.Primary
            )
        }
    }
}

@Preview(showBackground = true, name = "Dark Offline")
@Composable
private fun ACIOfflineStateDarkPreview() {
    ACITheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            ACIOfflineState(onRetry = {})
        }
    }
}
