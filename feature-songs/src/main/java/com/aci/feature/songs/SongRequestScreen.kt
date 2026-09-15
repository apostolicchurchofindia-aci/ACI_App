package com.aci.feature.songs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import com.aci.core.ui.components.ACIButton
import com.aci.core.ui.components.ACIButtonType
import com.aci.core.ui.components.ACICard
import com.aci.core.ui.components.ACIChip
import com.aci.core.ui.components.ACISectionHeader
import com.aci.core.ui.components.ACITopBar
import com.aci.core.ui.theme.ACISpacing
import com.aci.core.ui.theme.ACITheme

private val LANGUAGES = listOf("Telugu", "Tamil", "English")

@Composable
fun SongRequestScreen(
    onBack: () -> Unit,
    onSubmit: (titleEn: String, titleNative: String, language: String, notes: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var titleEn by remember { mutableStateOf("") }
    var titleNative by remember { mutableStateOf("") }
    var language by remember { mutableStateOf(LANGUAGES.first()) }
    var notes by remember { mutableStateOf("") }
    var submitted by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        ACITopBar(title = "Request a Song", onNavClick = onBack)

        if (submitted) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(ACISpacing.xxl),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(ACISpacing.sm))
                Text("Request submitted", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(ACISpacing.xxs))
                Text(
                    "An Admin will review it and add the song once approved.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            return@Column
        }

        LazyColumn(contentPadding = PaddingValues(bottom = ACISpacing.xxl)) {
            item {
                ACISectionHeader(
                    title = "Request a Song",
                    subtitle = "Don't see a song in the library? Ask for it — a super admin reviews every request before it's added."
                )
            }

            item {
                ACICard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = ACISpacing.md, vertical = ACISpacing.xs)
                ) {
                    Column(modifier = Modifier.padding(ACISpacing.md)) {
                        OutlinedTextField(
                            value = titleEn,
                            onValueChange = { titleEn = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Song title") },
                            singleLine = true
                        )
                        Spacer(modifier = Modifier.height(ACISpacing.sm))
                        OutlinedTextField(
                            value = titleNative,
                            onValueChange = { titleNative = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Native script title (optional)") },
                            singleLine = true
                        )
                        Spacer(modifier = Modifier.height(ACISpacing.sm))
                        Text("Language", style = MaterialTheme.typography.labelMedium)
                        Spacer(modifier = Modifier.height(ACISpacing.xxs))
                        Row(horizontalArrangement = Arrangement.spacedBy(ACISpacing.xs)) {
                            LANGUAGES.forEach { lang ->
                                ACIChip(text = lang, selected = language == lang, onClick = { language = lang })
                            }
                        }
                        Spacer(modifier = Modifier.height(ACISpacing.sm))
                        OutlinedTextField(
                            value = notes,
                            onValueChange = { notes = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Notes for the admin (optional)") },
                            placeholder = { Text("Where can this song be found? Do you know the composer?") },
                            minLines = 3
                        )
                        Spacer(modifier = Modifier.height(ACISpacing.xxs))
                        Text(
                            "We don't copy lyrics from other sites automatically — please only submit songs ACI has the rights to use.",
                            style = MaterialTheme.typography.labelSmall.copy(fontStyle = FontStyle.Italic),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(ACISpacing.md))
                        ACIButton(
                            text = "Submit request",
                            onClick = {
                                onSubmit(titleEn.trim(), titleNative.trim(), language, notes.trim())
                                submitted = true
                            },
                            type = ACIButtonType.Primary,
                            enabled = titleEn.isNotBlank(),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SongRequestScreenPreview() {
    ACITheme {
        SongRequestScreen(onBack = {}, onSubmit = { _, _, _, _ -> })
    }
}
