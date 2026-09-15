package com.aci.feature.prayer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aci.core.ui.components.ACIButton
import com.aci.core.ui.components.ACIButtonType
import com.aci.core.ui.components.ACITopBar
import com.aci.core.ui.theme.ACISpacing

@Composable
fun SubmitPrayerScreen(
    onBack: () -> Unit,
    onSubmit: (text: String, isAnonymous: Boolean, isPrivate: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }
    var isAnonymous by remember { mutableStateOf(false) }
    var isPrivate by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxSize()) {
        ACITopBar(title = "Submit a Prayer Request", onNavClick = onBack)

        Column(modifier = Modifier.fillMaxWidth().padding(ACISpacing.lg)) {
            Text(
                "How can we pray for you?",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(ACISpacing.sm))
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.fillMaxWidth().height(160.dp),
                placeholder = { Text("Share what's on your heart…") }
            )
            Spacer(modifier = Modifier.height(ACISpacing.md))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = isAnonymous, onCheckedChange = { isAnonymous = it })
                Text("Submit anonymously")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = isPrivate, onCheckedChange = { isPrivate = it })
                Text("Private (only visible to church leadership, not the wall)")
            }

            Spacer(modifier = Modifier.height(ACISpacing.lg))
            ACIButton(
                text = "Submit Prayer",
                onClick = { onSubmit(text, isAnonymous, isPrivate) },
                type = ACIButtonType.Primary,
                enabled = text.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
