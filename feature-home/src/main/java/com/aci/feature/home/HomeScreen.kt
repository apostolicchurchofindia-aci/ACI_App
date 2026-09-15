package com.aci.feature.home

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aci.core.domain.enum.Language as ContentLanguage
import com.aci.core.domain.enum.Role
import com.aci.core.domain.model.Branch
import com.aci.core.domain.model.DailyVerse
import com.aci.core.domain.model.forLanguage
import com.aci.core.domain.model.translationCodeFor
import com.aci.core.domain.model.Event
import com.aci.core.domain.model.Sermon
import com.aci.core.domain.model.Song
import com.aci.core.domain.model.User
import com.aci.core.domain.model.VerseVideo
import com.aci.core.domain.model.WeeklySong
import com.aci.core.ui.components.ACIButton
import com.aci.core.ui.components.ACIButtonType
import com.aci.core.ui.components.ACICard
import com.aci.core.ui.components.ACIChip
import com.aci.core.ui.theme.ACISpacing
import com.aci.core.ui.theme.ACITheme
import kotlinx.datetime.Instant

private val adminRoles = setOf(Role.BRANCH_ADMIN, Role.ACI_ADMIN, Role.SUPER_ADMIN)

/**
 * The Home dashboard — the most important screen per the product spec. All content is passed
 * in by the caller (loaded from JSON/Room, see `data-content`) rather than looked up here.
 */
@Composable
fun HomeScreen(
    preferredBranch: Branch,
    todayVerse: DailyVerse,
    todayVerseVideo: VerseVideo?,
    weeklySong: WeeklySong?,
    weeklySongDetails: Song?,
    latestSermon: Sermon,
    upcomingEvent: Event?,
    currentUser: User?,
    selectedLanguage: ContentLanguage,
    onOpenSong: (String) -> Unit,
    onOpenPrayer: () -> Unit,
    onOpenBible: () -> Unit,
    onOpenEvents: () -> Unit,
    onWatchSermons: () -> Unit,
    onOpenLogin: () -> Unit,
    onOpenRegister: () -> Unit,
    onOpenAdminRequests: () -> Unit,
    modifier: Modifier = Modifier
) {
    val strings = homeStringsFor(selectedLanguage)

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(bottom = ACISpacing.xxl)
    ) {
        item {
            AccountCard(
                currentUser = currentUser,
                strings = strings,
                onOpenLogin = onOpenLogin,
                onOpenRegister = onOpenRegister,
                onOpenAdminRequests = onOpenAdminRequests
            )
        }

        item {
            SectionCard {
                Text(strings.dailyVerse, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(ACISpacing.xs))
                Text(
                    text = "“${todayVerse.text.forLanguage(selectedLanguage)}”",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(ACISpacing.xxs))
                Text(
                    text = "— ${todayVerse.verseRef} (${translationCodeFor(selectedLanguage)})",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(ACISpacing.sm))
                Row(horizontalArrangement = Arrangement.spacedBy(ACISpacing.sm)) {
                    ACIButton(text = strings.read, onClick = onOpenBible, type = ACIButtonType.Primary)
                    ACIButton(text = strings.share, onClick = {}, type = ACIButtonType.Outlined)
                }
            }
        }

        item {
            if (todayVerseVideo != null) {
                SectionCard {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.PlayCircle,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(strings.watchAciSermons, style = MaterialTheme.typography.titleMedium)
                    }
                    Spacer(modifier = Modifier.height(ACISpacing.xs))
                    Text(todayVerseVideo.title, style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(ACISpacing.xxs))
                    Text(
                        text = todayVerseVideo.bibleReference,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(ACISpacing.sm))
                    ACIButton(
                        text = strings.playOnYouTube,
                        onClick = onWatchSermons,
                        type = ACIButtonType.Primary
                    )
                }
            }
        }

        item {
            if (weeklySong != null && weeklySongDetails != null) {
                SectionCard {
                    Text(strings.thisSundaysSong, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(ACISpacing.xs))
                    Text(weeklySongDetails.titleEn, style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(ACISpacing.xxs))
                    Row(horizontalArrangement = Arrangement.spacedBy(ACISpacing.xs)) {
                        weeklySong.key?.let { ACIChip(text = "Key: ${it.name}", onClick = {}) }
                        weeklySong.bpm?.let { ACIChip(text = "${it} BPM", onClick = {}) }
                        if (weeklySong.worshipLeaderName.isNotBlank()) {
                            ACIChip(text = weeklySong.worshipLeaderName, onClick = {})
                        }
                    }
                    Spacer(modifier = Modifier.height(ACISpacing.sm))
                    ACIButton(
                        text = strings.practiceSong,
                        onClick = { onOpenSong(weeklySongDetails.id) },
                        type = ACIButtonType.Primary
                    )
                }
            }
        }

        item {
            SectionCard {
                Text(strings.upcomingService, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(ACISpacing.xs))
                Text(preferredBranch.name, style = MaterialTheme.typography.bodyLarge)
                Text(
                    text = preferredBranch.address + ", " + preferredBranch.city,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(ACISpacing.sm))
                ACIButton(text = strings.addReminder, onClick = {}, type = ACIButtonType.Outlined)
            }
        }

        item {
            SectionCard {
                Text(strings.latestSermon, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(ACISpacing.xs))
                Text(latestSermon.title, style = MaterialTheme.typography.bodyLarge)
                Text(
                    text = "${latestSermon.speaker} · ${latestSermon.topic}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(ACISpacing.sm))
                ACIButton(text = strings.watch, onClick = {}, type = ACIButtonType.Primary)
            }
        }

        item {
            SectionCard {
                Text(strings.prayer, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(ACISpacing.xs))
                Text(strings.prayerPrompt, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(ACISpacing.sm))
                ACIButton(text = strings.submitPrayer, onClick = onOpenPrayer, type = ACIButtonType.Primary)
            }
        }

        item {
            if (upcomingEvent != null) {
                SectionCard {
                    Text(strings.events, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(ACISpacing.xs))
                    Text(upcomingEvent.title, style = MaterialTheme.typography.bodyLarge)
                    Text(
                        text = upcomingEvent.locationName,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(ACISpacing.sm))
                    ACIButton(text = strings.seeAllEvents, onClick = onOpenEvents, type = ACIButtonType.Outlined)
                }
            }
        }
    }
}


@Composable
private fun AccountCard(
    currentUser: User?,
    strings: HomeStrings,
    onOpenLogin: () -> Unit,
    onOpenRegister: () -> Unit,
    onOpenAdminRequests: () -> Unit
) {
    // Signed-in identity and sign-out live in the app bar now, so an ordinary member has
    // nothing left to show here — only admins keep a card, for their shortcut.
    if (currentUser != null && currentUser.role !in adminRoles) return

    SectionCard {
        if (currentUser == null) {
            Text(strings.registerLogin, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(ACISpacing.xxs))
            Text(
                strings.accountBlurb,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(ACISpacing.sm))
            Row(horizontalArrangement = Arrangement.spacedBy(ACISpacing.sm)) {
                ACIButton(text = strings.register, onClick = onOpenRegister, type = ACIButtonType.Primary)
                ACIButton(text = strings.login, onClick = onOpenLogin, type = ACIButtonType.Outlined)
            }
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.AdminPanelSettings,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(ACISpacing.xxs))
                Text(
                    "${currentUser.role.name.replace('_', ' ')} ${strings.accessSuffix}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.height(ACISpacing.sm))
            ACIButton(text = strings.songRequestsAdmin, onClick = onOpenAdminRequests, type = ACIButtonType.Primary)
        }
    }
}

@Composable
private fun SectionCard(content: @Composable androidx.compose.foundation.layout.ColumnScope.() -> Unit) {
    ACICard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ACISpacing.md, vertical = ACISpacing.xs)
    ) {
        Column(modifier = Modifier.padding(ACISpacing.md), content = content)
    }
}

@Preview(showBackground = true, name = "Home — preview fixture")
@Composable
private fun HomeScreenPreview() {
    ACITheme {
        HomeScreen(
            preferredBranch = Branch(
                id = "preview-branch",
                name = "ACI Bharath Nagar (Ernavoor)",
                address = "No 20, 2nd Street, Bharath Nagar, Ernavoor",
                city = "Thiruvottiyur, Chennai"
            ),
            todayVerse = DailyVerse(
                date = kotlinx.datetime.LocalDate(2026, 9, 11),
                verseRef = "Isaiah 40:31",
                text = com.aci.core.domain.model.BibleVerseText(en = "But they that wait upon the LORD shall renew their strength."),
                translationCode = "KJV"
            ),
            todayVerseVideo = null,
            weeklySong = null,
            weeklySongDetails = null,
            latestSermon = Sermon(
                id = "preview-sermon",
                title = "The Faith That Moves Mountains",
                speaker = "Pastor Dr. Rev. D.D.K. Ratna Raju",
                topic = "Faith",
                preachedAt = Instant.DISTANT_PAST
            ),
            upcomingEvent = null,
            currentUser = null,
            selectedLanguage = ContentLanguage.ENGLISH,
            onOpenSong = {},
            onOpenPrayer = {},
            onOpenBible = {},
            onOpenEvents = {},
            onWatchSermons = {},
            onOpenLogin = {},
            onOpenRegister = {},
            onOpenAdminRequests = {}
        )
    }
}
