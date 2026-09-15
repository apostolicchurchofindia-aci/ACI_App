package com.aci.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aci.core.domain.model.AboutInfo
import com.aci.core.domain.model.CoreBelief
import com.aci.core.ui.components.ACICard
import com.aci.core.ui.components.ACIChip
import com.aci.core.ui.components.ACISectionHeader
import com.aci.core.ui.theme.ACISpacing
import com.aci.core.ui.theme.ACITheme

/**
 * About ACI — content and photos sourced from the church's own site
 * (sites.google.com/view/apostolicchurchofindia).
 */
@Composable
fun AboutScreen(
    about: AboutInfo,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(bottom = ACISpacing.xxl)
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.photo_church_exterior),
                    contentDescription = "An ACI church building",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(ACISpacing.lg),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.aci_logo),
                        contentDescription = "ACI logo",
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(ACISpacing.sm))
                    Text(
                        text = "The Apostolic Church of India",
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(ACISpacing.xxs))
                    Text(
                        text = "Founded ${about.foundedYear} · ${about.foundingCity}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(ACISpacing.sm))
                    Text(
                        text = about.tagline,
                        style = MaterialTheme.typography.bodyLarge.copy(fontStyle = FontStyle.Italic),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        item {
            SectionCard {
                Text("Our Mission", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(ACISpacing.xs))
                Text(about.missionStatement, style = MaterialTheme.typography.bodyLarge)
            }
        }

        item {
            if (about.foundingStory.isNotBlank()) {
                SectionCard {
                    Text("Our Story", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(ACISpacing.xs))
                    Text(about.foundingStory, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }

        item {
            ACISectionHeader(title = "Our Core Values")
        }
        items(about.coreBeliefs) { belief -> CoreBeliefRow(belief) }

        item {
            SectionCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Person, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(ACISpacing.xs))
                    Text("Leadership", style = MaterialTheme.typography.titleMedium)
                }
                Spacer(modifier = Modifier.height(ACISpacing.sm))
                LeaderRow(
                    photoRes = R.drawable.photo_founder,
                    name = about.founderName,
                    title = about.founderTitle
                )
                Spacer(modifier = Modifier.height(ACISpacing.md))
                LeaderRow(
                    photoRes = R.drawable.photo_coleader,
                    name = about.coLeaderName,
                    title = about.coLeaderTitle
                )
            }
        }

        item {
            SectionCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Groups, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(ACISpacing.xs))
                    Text("Ministries", style = MaterialTheme.typography.titleMedium)
                }
                Spacer(modifier = Modifier.height(ACISpacing.sm))
                FlowChips(about.ministries)
            }
        }

        item {
            if (about.additionalLocations.isNotEmpty()) {
                SectionCard {
                    Text("Also Serving", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(ACISpacing.xxs))
                    Text(
                        "ACI also has a presence in:",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(ACISpacing.sm))
                    FlowChips(about.additionalLocations)
                }
            }
        }

        item {
            SectionCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(ACISpacing.xs))
                    Text("Connect", style = MaterialTheme.typography.titleMedium)
                }
                Spacer(modifier = Modifier.height(ACISpacing.sm))
                FlowChips(listOfNotNull(
                    about.instagramHandle.ifBlank { null },
                    about.youtubeHandle.ifBlank { null }
                ))
            }
        }
    }
}

@Composable
private fun LeaderRow(photoRes: Int, name: String, title: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(photoRes),
            contentDescription = name,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(ACISpacing.sm))
        Column {
            Text(name, style = MaterialTheme.typography.bodyLarge)
            Text(title, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun CoreBeliefRow(belief: CoreBelief) {
    SectionCard {
        Text(belief.title, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(ACISpacing.xxs))
        Text(belief.description, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
private fun FlowChips(items: List<String>) {
    // One chip per line: a fixed-width Row squishes long labels against each other
    // (no flow-layout dependency available in this module), so stack instead of pair.
    Column(verticalArrangement = Arrangement.spacedBy(ACISpacing.xs)) {
        items.forEach { label -> ACIChip(text = label, onClick = {}) }
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

private val previewAbout = AboutInfo(
    foundedYear = 1970,
    foundingCity = "Chennai, Tamil Nadu",
    tagline = "A vibrant community of faith, dedicated to spreading the Gospel and serving those in need.",
    missionStatement = "Welcome to the Apostolic Church of India (ACI)… established in 1970 in Chennai.",
    coreBeliefs = listOf(
        CoreBelief("Faith", "We build a strong foundation through worship, prayer, and scripture study."),
        CoreBelief("Compassion", "We extend a helping hand to those in need.")
    ),
    ministries = listOf("Sunday Worship Services", "Weekly Prayer Meetings", "Youth & Family Programs"),
    additionalLocations = listOf("Thiruvottiyur", "Ennore", "Velachery"),
    founderName = "Dr. Rev. D.D.K Ratna Raju",
    founderTitle = "Founder & President",
    coLeaderName = "Mother Shanthi Ratna Raju",
    coLeaderTitle = "President",
    instagramHandle = "@acichurchindia",
    youtubeHandle = "@theapostolicchurchofindia"
)

@Preview(showBackground = true, name = "About ACI")
@Composable
private fun AboutScreenPreview() {
    ACITheme {
        AboutScreen(about = previewAbout)
    }
}
