package com.aci.feature.prayer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.aci.core.domain.model.PrayerRequest
import com.aci.core.domain.model.Testimony
import com.aci.core.ui.components.ACIButton
import com.aci.core.ui.components.ACIButtonType
import com.aci.core.ui.components.ACICard
import com.aci.core.ui.components.ACIChip
import com.aci.core.ui.components.ACIEmptyState
import com.aci.core.ui.theme.ACISpacing

private val tabs = listOf("Requests", "Answered", "Testimonies")

@Composable
fun PrayerWallScreen(
    activeRequests: List<PrayerRequest>,
    answeredRequests: List<PrayerRequest>,
    testimonies: List<Testimony>,
    onPrayFor: (PrayerRequest) -> Unit,
    onMarkAnswered: (PrayerRequest) -> Unit,
    onSubmitPrayer: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            if (selectedTab == 0) {
                FloatingActionButton(onClick = onSubmitPrayer) {
                    Icon(Icons.Default.VolunteerActivism, contentDescription = "Submit a prayer request")
                }
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }

            when (selectedTab) {
                0 -> RequestsList(
                    requests = activeRequests,
                    emptyTitle = "No prayer requests yet",
                    emptySubtitle = "Be the first — tap the button below to share what's on your heart.",
                    showPrayButton = true,
                    showAnsweredButton = true,
                    onPrayFor = onPrayFor,
                    onMarkAnswered = onMarkAnswered
                )
                1 -> RequestsList(
                    requests = answeredRequests,
                    emptyTitle = "No answered prayers yet",
                    emptySubtitle = "When a prayer is marked answered, it'll show up here to encourage others.",
                    showPrayButton = false,
                    showAnsweredButton = false,
                    onPrayFor = onPrayFor,
                    onMarkAnswered = onMarkAnswered
                )
                2 -> TestimoniesList(testimonies)
            }
        }
    }
}

@Composable
private fun RequestsList(
    requests: List<PrayerRequest>,
    emptyTitle: String,
    emptySubtitle: String,
    showPrayButton: Boolean,
    showAnsweredButton: Boolean,
    onPrayFor: (PrayerRequest) -> Unit,
    onMarkAnswered: (PrayerRequest) -> Unit
) {
    if (requests.isEmpty()) {
        ACIEmptyState(icon = Icons.Default.FavoriteBorder, title = emptyTitle, subtitle = emptySubtitle)
        return
    }
    LazyColumn(contentPadding = PaddingValues(ACISpacing.md)) {
        items(requests, key = { it.id }) { request ->
            ACICard(modifier = Modifier.fillMaxWidth().padding(vertical = ACISpacing.xxs)) {
                Column(modifier = Modifier.fillMaxWidth().padding(ACISpacing.md)) {
                    if (request.isAnonymous) {
                        ACIChip(text = "Anonymous", onClick = {})
                        Spacer(modifier = Modifier.height(ACISpacing.xs))
                    }
                    Text(request.text, style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(ACISpacing.sm))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(ACISpacing.xxs))
                            Text(
                                "${request.prayedCount} prayed",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Row {
                            if (showAnsweredButton) {
                                TextButton(onClick = { onMarkAnswered(request) }) { Text("Mark Answered") }
                            }
                            if (showPrayButton) {
                                ACIButton(text = "Pray", onClick = { onPrayFor(request) }, type = ACIButtonType.Outlined)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TestimoniesList(testimonies: List<Testimony>) {
    if (testimonies.isEmpty()) {
        ACIEmptyState(
            icon = Icons.Default.FavoriteBorder,
            title = "No testimonies yet",
            subtitle = "Shared testimonies appear here once approved."
        )
        return
    }
    LazyColumn(contentPadding = PaddingValues(ACISpacing.md)) {
        items(testimonies, key = { it.id }) { testimony ->
            ACICard(modifier = Modifier.fillMaxWidth().padding(vertical = ACISpacing.xxs)) {
                Column(modifier = Modifier.fillMaxWidth().padding(ACISpacing.md)) {
                    Text(testimony.title, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(ACISpacing.xxs))
                    Text(testimony.description, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
