package com.aci.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aci.core.ui.components.ACIButton
import com.aci.core.ui.components.ACIButtonType
import com.aci.core.ui.components.ACIBottomNav
import com.aci.core.ui.components.ACICard
import com.aci.core.ui.components.ACIChip
import com.aci.core.ui.components.ACIConfirmationDialog
import com.aci.core.ui.components.ACIEmptyState
import com.aci.core.ui.components.ACIErrorState
import com.aci.core.ui.components.ACILoading
import com.aci.core.ui.components.ACIOfflineState
import com.aci.core.ui.components.ACISectionHeader
import com.aci.core.ui.components.ACIShimmer
import com.aci.core.ui.components.ACITopBar
import com.aci.core.ui.components.BottomNavItem
import com.aci.core.ui.components.defaultBottomNavItems
import com.aci.core.ui.theme.ACISpacing
import com.aci.core.ui.theme.ACITheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DesignSystemShowcase(modifier: Modifier = Modifier) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedChip by remember { mutableStateOf("All") }
    var selectedNav by remember { mutableStateOf("home") }

    Scaffold(
        modifier = modifier,
        topBar = {
            ACITopBar(
                title = "ACI Design System",
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        },
        bottomBar = {
            ACIBottomNav(
                items = defaultBottomNavItems,
                currentRoute = selectedNav,
                onNavigate = { selectedNav = it }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(ACISpacing.md)
        ) {
            SectionTitle("Typography")
            TypographySection()

            Divider(modifier = Modifier.padding(horizontal = ACISpacing.md))

            SectionTitle("Buttons")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ACISpacing.md),
                horizontalArrangement = Arrangement.spacedBy(ACISpacing.sm)
            ) {
                ACIButton(text = "Primary", onClick = {}, type = ACIButtonType.Primary, modifier = Modifier.weight(1f))
                ACIButton(text = "Secondary", onClick = {}, type = ACIButtonType.Secondary, modifier = Modifier.weight(1f))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ACISpacing.md),
                horizontalArrangement = Arrangement.spacedBy(ACISpacing.sm)
            ) {
                ACIButton(text = "Outlined", onClick = {}, type = ACIButtonType.Outlined, modifier = Modifier.weight(1f))
                ACIButton(text = "Text", onClick = {}, type = ACIButtonType.Text, modifier = Modifier.weight(1f))
            }

            Divider(modifier = Modifier.padding(horizontal = ACISpacing.md))

            SectionTitle("Cards")
            ACICard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ACISpacing.md)
            ) {
                Column(modifier = Modifier.padding(ACISpacing.md)) {
                    Text("Welcome to ACI Church", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(ACISpacing.xs))
                    Text(
                        "Join us in worship and fellowship every Sunday. Experience the love of Christ.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(ACISpacing.sm))
                    ACIButton(text = "Learn More", onClick = {}, type = ACIButtonType.Secondary)
                }
            }

            Divider(modifier = Modifier.padding(horizontal = ACISpacing.md))

            SectionTitle("Section Headers")
            ACISectionHeader(
                title = "Upcoming Events",
                subtitle = "Events this week",
                actionText = "See All",
                onAction = {}
            )

            Divider(modifier = Modifier.padding(horizontal = ACISpacing.md))

            SectionTitle("Chips")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ACISpacing.md),
                horizontalArrangement = Arrangement.spacedBy(ACISpacing.xs)
            ) {
                listOf("All", "Songs", "Sermons", "Events").forEach { label ->
                    ACIChip(
                        text = label,
                        selected = selectedChip == label,
                        onClick = { selectedChip = label },
                        leadingIcon = if (label == "Songs") Icons.Default.Favorite else null
                    )
                }
            }

            Divider(modifier = Modifier.padding(horizontal = ACISpacing.md))

            SectionTitle("States - Loading")
            ACILoading(message = "Loading content...")

            Divider(modifier = Modifier.padding(horizontal = ACISpacing.md))

            SectionTitle("States - Empty")
            ACIEmptyState(
                icon = Icons.Default.Inbox,
                title = "No Items Found",
                subtitle = "Check back later for new content.",
                cta = "Refresh",
                onClick = {}
            )

            Divider(modifier = Modifier.padding(horizontal = ACISpacing.md))

            SectionTitle("States - Error")
            ACIErrorState(
                message = "Unable to connect to server. Please try again.",
                onRetry = {}
            )

            Divider(modifier = Modifier.padding(horizontal = ACISpacing.md))

            SectionTitle("States - Offline")
            ACIOfflineState(onRetry = {})

            Divider(modifier = Modifier.padding(horizontal = ACISpacing.md))

            SectionTitle("Shimmer / Skeleton")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ACISpacing.md),
                verticalArrangement = Arrangement.spacedBy(ACISpacing.sm)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(ACISpacing.md)
                ) {
                    ACIShimmer(modifier = Modifier.size(56.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(ACISpacing.xs),
                        modifier = Modifier.weight(1f)
                    ) {
                        ACIShimmer(modifier = Modifier.fillMaxWidth().height(18.dp))
                        ACIShimmer(modifier = Modifier.fillMaxWidth().height(14.dp))
                    }
                }
                ACIShimmer(modifier = Modifier.fillMaxWidth().height(100.dp))
            }

            Divider(modifier = Modifier.padding(horizontal = ACISpacing.md))

            SectionTitle("Dialogs")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ACISpacing.md),
                horizontalArrangement = Arrangement.spacedBy(ACISpacing.sm)
            ) {
                ACIButton(
                    text = "Show Dialog",
                    onClick = { showDialog = true },
                    type = ACIButtonType.Outlined,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(ACISpacing.xl))
        }
    }

    if (showDialog) {
        ACIConfirmationDialog(
            title = "Confirm Logout",
            message = "Are you sure you want to log out of your account?",
            confirmText = "Logout",
            dismissText = "Cancel",
            onConfirm = { showDialog = false },
            onDismiss = { showDialog = false }
        )
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(horizontal = ACISpacing.md, vertical = ACISpacing.xs)
    )
}

@Composable
private fun TypographySection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ACISpacing.md),
        verticalArrangement = Arrangement.spacedBy(ACISpacing.xs)
    ) {
        Text("Display Large - Welcome", style = MaterialTheme.typography.displaySmall)
        Text("Headline Medium", style = MaterialTheme.typography.headlineMedium)
        Text("Headline Small", style = MaterialTheme.typography.headlineSmall)
        Text("Title Large", style = MaterialTheme.typography.titleLarge)
        Text("Title Medium", style = MaterialTheme.typography.titleMedium)
        Text("Body Large - Lorem ipsum dolor sit amet, consectetur adipiscing elit.", style = MaterialTheme.typography.bodyLarge)
        Text("Body Medium - Lorem ipsum dolor sit amet.", style = MaterialTheme.typography.bodyMedium)
        Text("Label Large - Call to action", style = MaterialTheme.typography.labelLarge)
    }
}

@Preview(showBackground = true, name = "Light Design System")
@Composable
private fun DesignSystemShowcaseLightPreview() {
    ACITheme(useDarkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            DesignSystemShowcase()
        }
    }
}

@Preview(showBackground = true, name = "Dark Design System")
@Composable
private fun DesignSystemShowcaseDarkPreview() {
    ACITheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            DesignSystemShowcase()
        }
    }
}
