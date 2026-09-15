package com.aci.core.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.aci.core.ui.theme.ACITheme

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

val defaultBottomNavItems = listOf(
    BottomNavItem("home", "Home", Icons.Default.Home),
    BottomNavItem("bible", "Bible", Icons.Default.MenuBook),
    BottomNavItem("songs", "Songs", Icons.Default.LibraryMusic),
    BottomNavItem("events", "Events", Icons.Default.CalendarMonth),
    BottomNavItem("myaci", "My ACI", Icons.Default.AccountCircle)
)

val defaultBottomNavItemsTamil = listOf(
    BottomNavItem("home", "முகப்பு", Icons.Default.Home),
    BottomNavItem("bible", "பைபிள்", Icons.Default.MenuBook),
    BottomNavItem("songs", "பாடல்கள்", Icons.Default.LibraryMusic),
    BottomNavItem("events", "நிகழ்ச்சிகள்", Icons.Default.CalendarMonth),
    BottomNavItem("myaci", "என் ACI", Icons.Default.AccountCircle)
)

val defaultBottomNavItemsTelugu = listOf(
    BottomNavItem("home", "హోమ్", Icons.Default.Home),
    BottomNavItem("bible", "బైబిల్", Icons.Default.MenuBook),
    BottomNavItem("songs", "పాటలు", Icons.Default.LibraryMusic),
    BottomNavItem("events", "కార్యక్రమాలు", Icons.Default.CalendarMonth),
    BottomNavItem("myaci", "నా ACI", Icons.Default.AccountCircle)
)

@Composable
fun ACIBottomNav(
    items: List<BottomNavItem>,
    currentRoute: String,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { onNavigate(item.route) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.secondaryContainer
                )
            )
        }
    }
}

@Preview(showBackground = true, name = "Light BottomNav")
@Composable
private fun ACIBottomNavLightPreview() {
    ACITheme(useDarkTheme = false) {
        ACIBottomNav(
            items = defaultBottomNavItems,
            currentRoute = "home",
            onNavigate = {}
        )
    }
}

@Preview(showBackground = true, name = "Tamil BottomNav")
@Composable
private fun ACIBottomNavTamilPreview() {
    ACITheme(useDarkTheme = false) {
        ACIBottomNav(
            items = defaultBottomNavItemsTamil,
            currentRoute = "bible",
            onNavigate = {}
        )
    }
}

@Preview(showBackground = true, name = "Dark BottomNav")
@Composable
private fun ACIBottomNavDarkPreview() {
    ACITheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            ACIBottomNav(
                items = defaultBottomNavItems,
                currentRoute = "home",
                onNavigate = {}
            )
        }
    }
}
