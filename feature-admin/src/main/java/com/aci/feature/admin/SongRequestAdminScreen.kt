package com.aci.feature.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aci.core.domain.model.SongRequest
import com.aci.core.ui.components.ACIButton
import com.aci.core.ui.components.ACIButtonType
import com.aci.core.ui.components.ACICard
import com.aci.core.ui.components.ACIChip
import com.aci.core.ui.components.ACISectionHeader
import com.aci.core.ui.components.ACITopBar
import com.aci.core.ui.theme.ACISpacing
import com.aci.core.ui.theme.ACITheme
import kotlinx.datetime.Instant

@Composable
fun SongRequestAdminScreen(
    pending: List<SongRequest>,
    signedInAsName: String,
    onBack: () -> Unit,
    onApprove: (SongRequest) -> Unit,
    onReject: (SongRequest) -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ACITopBar(
            title = "Song Requests (Admin)",
            onNavClick = onBack,
            actions = {
                IconButton(onClick = onLogout) {
                    Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = "Sign out")
                }
            }
        )

        LazyColumn(contentPadding = PaddingValues(bottom = ACISpacing.xxl)) {
            item {
                ACISectionHeader(
                    title = "Pending requests",
                    subtitle = "${pending.size} awaiting review · Signed in as $signedInAsName"
                )
            }

            if (pending.isEmpty()) {
                item {
                    Text(
                        text = "Nothing to review right now.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(ACISpacing.md)
                    )
                }
            }

            items(pending, key = { it.id }) { request ->
                ACICard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = ACISpacing.md, vertical = ACISpacing.xxs)
                ) {
                    Column(modifier = Modifier.padding(ACISpacing.md)) {
                        Text(request.titleEn, style = MaterialTheme.typography.titleMedium)
                        if (request.titleNative.isNotBlank()) {
                            Text(
                                request.titleNative,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Spacer(modifier = Modifier.height(ACISpacing.xs))
                        ACIChip(text = request.language.ifBlank { "Unspecified" }, selected = false, onClick = {})
                        if (request.notes.isNotBlank()) {
                            Spacer(modifier = Modifier.height(ACISpacing.xs))
                            Text(
                                request.notes,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Spacer(modifier = Modifier.height(ACISpacing.md))
                        Row(horizontalArrangement = Arrangement.spacedBy(ACISpacing.sm)) {
                            ACIButton(text = "Approve", onClick = { onApprove(request) }, type = ACIButtonType.Primary)
                            ACIButton(text = "Reject", onClick = { onReject(request) }, type = ACIButtonType.Outlined)
                        }
                    }
                }
            }
        }
    }
}

private val previewRequests = listOf(
    SongRequest(
        id = "preview-1",
        titleEn = "Ennai Kaanbavarae",
        titleNative = "என்னைக் காண்பவரே",
        language = "Tamil",
        notes = "Popular at Sunday worship",
        createdAt = Instant.DISTANT_PAST
    )
)

@Preview(showBackground = true)
@Composable
private fun SongRequestAdminScreenPreview() {
    ACITheme {
        SongRequestAdminScreen(
            pending = previewRequests,
            signedInAsName = "Ruth Abraham",
            onBack = {},
            onApprove = {},
            onReject = {},
            onLogout = {}
        )
    }
}
