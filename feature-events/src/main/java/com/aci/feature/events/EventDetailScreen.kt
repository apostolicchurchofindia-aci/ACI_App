package com.aci.feature.events

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.aci.core.domain.model.Event
import com.aci.core.ui.components.ACIButton
import com.aci.core.ui.components.ACIButtonType
import com.aci.core.ui.components.ACICard
import com.aci.core.ui.components.ACITopBar
import com.aci.core.ui.theme.ACISpacing

@Composable
fun EventDetailScreen(
    event: Event,
    registeredConfirmationId: String?,
    onBack: () -> Unit,
    onRegister: (name: String, phone: String, seats: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        ACITopBar(title = event.title, onNavClick = onBack)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(ACISpacing.lg)
        ) {
            Text(event.title, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(ACISpacing.sm))

            event.startAt?.let { start ->
                InfoRow(icon = Icons.Default.CalendarMonth, text = formatEventDate(start))
            }
            InfoRow(icon = Icons.Default.LocationOn, text = "${event.locationName}\n${event.address}")

            Spacer(modifier = Modifier.height(ACISpacing.md))
            Text(event.description, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(ACISpacing.lg))

            when {
                registeredConfirmationId != null -> ConfirmationCard(registeredConfirmationId)
                event.registrationRequired -> RegistrationForm(maxSeats = event.maxSeats, onRegister = onRegister)
                else -> Text(
                    "No registration required — just come!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun InfoRow(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    Row(verticalAlignment = Alignment.Top, modifier = Modifier.padding(vertical = ACISpacing.xxs)) {
        Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.width(ACISpacing.xs))
        Text(text, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
private fun RegistrationForm(maxSeats: Int, onRegister: (String, String, Int) -> Unit) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var seats by remember { mutableIntStateOf(1) }

    ACICard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth().padding(ACISpacing.md)) {
            Text("Register", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(ACISpacing.sm))
            OutlinedTextField(
                value = name, onValueChange = { name = it },
                label = { Text("Full name") }, modifier = Modifier.fillMaxWidth(), singleLine = true
            )
            Spacer(modifier = Modifier.height(ACISpacing.sm))
            OutlinedTextField(
                value = phone, onValueChange = { phone = it },
                label = { Text("Phone number") }, modifier = Modifier.fillMaxWidth(), singleLine = true
            )
            Spacer(modifier = Modifier.height(ACISpacing.sm))
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(ACISpacing.md)) {
                Text("Seats:", style = MaterialTheme.typography.bodyMedium)
                ACIButton(text = "−", onClick = { if (seats > 1) seats-- }, type = ACIButtonType.Outlined)
                Text(seats.toString(), style = MaterialTheme.typography.titleMedium)
                ACIButton(
                    text = "+",
                    onClick = { if (maxSeats == 0 || seats < maxSeats) seats++ },
                    type = ACIButtonType.Outlined
                )
            }
            Spacer(modifier = Modifier.height(ACISpacing.md))
            ACIButton(
                text = "Confirm Registration",
                onClick = { onRegister(name, phone, seats) },
                type = ACIButtonType.Primary,
                enabled = name.isNotBlank() && phone.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ConfirmationCard(registrationId: String) {
    ACICard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(ACISpacing.lg),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(ACISpacing.sm))
            Text("You're registered!", style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(ACISpacing.xxs))
            Text(
                "Confirmation ID: $registrationId",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}
