package com.aci.feature.events

import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.aci.core.domain.model.Event
import com.aci.core.ui.components.ACICard
import com.aci.core.ui.components.ACIChip
import com.aci.core.ui.components.ACIEmptyState
import com.aci.core.ui.components.ACISectionHeader
import com.aci.core.ui.theme.ACISpacing
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun EventsListScreen(
    events: List<Event>,
    onEventClick: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    if (events.isEmpty()) {
        ACIEmptyState(
            icon = Icons.Default.CalendarMonth,
            title = "No upcoming events",
            subtitle = "Check back soon — new events will appear here."
        )
        return
    }

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(bottom = ACISpacing.xxl)
    ) {
        item { ACISectionHeader(title = "Events", subtitle = "${events.size} upcoming") }
        items(events, key = { it.id }) { event -> EventRow(event, onClick = { onEventClick(event) }) }
    }
}

@Composable
private fun EventRow(event: Event, onClick: () -> Unit) {
    ACICard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ACISpacing.md, vertical = ACISpacing.xxs)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(ACISpacing.md)) {
            ACIChip(text = event.category.name.replace('_', ' '), onClick = {})
            Spacer(modifier = Modifier.height(ACISpacing.xs))
            Text(event.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(ACISpacing.xxs))
            event.startAt?.let { start ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.CalendarMonth,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(ACISpacing.xxs))
                    Text(formatEventDate(start), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(ACISpacing.xxs))
                Text(event.locationName, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

fun formatEventDate(instant: Instant): String {
    val dt = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    val month = dt.month.name.lowercase().replaceFirstChar { it.uppercase() }.take(3)
    val hour12 = if (dt.hour % 12 == 0) 12 else dt.hour % 12
    val ampm = if (dt.hour < 12) "AM" else "PM"
    val minute = dt.minute.toString().padStart(2, '0')
    return "$month ${dt.dayOfMonth}, ${dt.year} · $hour12:$minute $ampm"
}
