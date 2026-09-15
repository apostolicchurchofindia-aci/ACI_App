package com.aci.church.app

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aci.core.domain.enum.Language as ContentLanguage
import com.aci.core.ui.theme.ACISpacing

fun ContentLanguage.displayLabel(): String = when (this) {
    ContentLanguage.ENGLISH -> "English"
    ContentLanguage.TAMIL -> "தமிழ்"
    ContentLanguage.TELUGU -> "తెలుగు"
}

private fun ContentLanguage.selectLanguageLabel(): String = when (this) {
    ContentLanguage.ENGLISH -> "Select content language"
    ContentLanguage.TAMIL -> "மொழியைத் தேர்ந்தெடுக்கவும்"
    ContentLanguage.TELUGU -> "భాషను ఎంచుకోండి"
}

private fun ContentLanguage.notificationsLabel(): String = when (this) {
    ContentLanguage.ENGLISH -> "Notifications"
    ContentLanguage.TAMIL -> "அறிவிப்புகள்"
    ContentLanguage.TELUGU -> "నోటిఫికేషన్‌లు"
}

private fun ContentLanguage.welcomeLabel(): String = when (this) {
    ContentLanguage.ENGLISH -> "Welcome"
    ContentLanguage.TAMIL -> "வரவேற்கிறோம்"
    ContentLanguage.TELUGU -> "స్వాగతం"
}

private fun ContentLanguage.signOutLabel(): String = when (this) {
    ContentLanguage.ENGLISH -> "Sign out"
    ContentLanguage.TAMIL -> "வெளியேறு"
    ContentLanguage.TELUGU -> "సైన్ అవుట్"
}

/** Second app-bar line: who's signed in, and a way out. Only shown when signed in. */
@Composable
fun AppBarSignedInLine(
    userName: String,
    selectedLanguage: ContentLanguage,
    onLogout: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        // The greeting yields space first: Tamil and Telugu labels are much longer than the
        // English ones, and without this "Sign out" wraps mid-word into the language picker.
        Text(
            text = "${selectedLanguage.welcomeLabel()} $userName",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f, fill = false)
        )
        Text(
            text = selectedLanguage.signOutLabel(),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            softWrap = false,
            modifier = Modifier
                .padding(start = ACISpacing.sm)
                .clickable(onClick = onLogout)
        )
    }
}

/** Language picker and notification bell, shown in the app bar beside the ACI Church title. */
@Composable
fun AppBarActions(
    selectedLanguage: ContentLanguage,
    onLanguageSelected: (ContentLanguage) -> Unit
) {
    LanguageDropdown(selectedLanguage = selectedLanguage, onLanguageSelected = onLanguageSelected)
    IconButton(onClick = {}) {
        Icon(Icons.Default.Notifications, contentDescription = selectedLanguage.notificationsLabel())
    }
}

@Composable
private fun LanguageDropdown(
    selectedLanguage: ContentLanguage,
    onLanguageSelected: (ContentLanguage) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Surface(
        shape = RoundedCornerShape(50),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.clickable { expanded = true }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = ACISpacing.sm, vertical = ACISpacing.xxs),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Language,
                contentDescription = selectedLanguage.selectLanguageLabel(),
                modifier = Modifier.width(18.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(selectedLanguage.displayLabel(), style = MaterialTheme.typography.labelLarge)
            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
        }
    }

    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        ContentLanguage.entries.forEach { language ->
            DropdownMenuItem(
                text = { Text(language.displayLabel()) },
                onClick = {
                    onLanguageSelected(language)
                    expanded = false
                }
            )
        }
    }
}
