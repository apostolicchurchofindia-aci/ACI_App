package com.aci.feature.bible

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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aci.core.domain.enum.Language
import com.aci.core.domain.model.BibleBook
import com.aci.core.domain.model.READING_PLAN_LENGTH_DAYS
import com.aci.core.domain.model.ReadingPlanDay
import com.aci.core.domain.model.ReadingPlanState
import com.aci.core.domain.model.chapterKeys
import com.aci.core.domain.model.chaptersRead
import com.aci.core.domain.model.completedAt
import com.aci.core.domain.model.completedCount
import com.aci.core.domain.model.currentDay
import com.aci.core.domain.model.isComplete
import com.aci.core.domain.model.label
import com.aci.core.ui.components.ACIButton
import com.aci.core.ui.components.ACIButtonType
import com.aci.core.ui.components.ACICard
import com.aci.core.ui.components.ACITopBar
import com.aci.core.ui.theme.ACISpacing
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

@Composable
fun ReadingPlanScreen(
    plan: List<ReadingPlanDay>,
    state: ReadingPlanState?,
    booksById: Map<String, BibleBook>,
    language: Language,
    today: LocalDate,
    /** Everyone can read the plan; only signed-in members get their progress saved. */
    isRegistered: Boolean,
    onStart: () -> Unit,
    onSignIn: () -> Unit,
    onOpenDay: (Int) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val strings = bibleStringsFor(language)

    Column(modifier = modifier.fillMaxWidth()) {
        ACITopBar(title = strings.readingPlanTitle, onNavClick = onBack)

        if (!isRegistered) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(bottom = ACISpacing.xxl)
            ) {
                item { SignInPrompt(strings = strings, onSignIn = onSignIn) }
                items(plan, key = { it.dayNumber }) { day ->
                    PlanDayRow(
                        day = day,
                        strings = strings,
                        booksById = booksById,
                        language = language,
                        onOpenDay = onOpenDay
                    )
                }
            }
        } else if (state == null) {
            PlanIntro(strings = strings, onStart = onStart)
        } else {
            val currentDay = state.currentDay(today)
            val completed = plan.completedCount(state)
            // "Behind" counts days already due that still aren't done — the current day doesn't
            // count against the member until it's over.
            val behind = plan.count { it.dayNumber < currentDay && !it.isComplete(state) }

            // Two header items precede the day list, so day N sits at index N + 1.
            val listState = rememberLazyListState(initialFirstVisibleItemIndex = currentDay + 1)

            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(bottom = ACISpacing.xxl)
            ) {
                item {
                    ProgressCard(
                        strings = strings,
                        completed = completed,
                        behind = behind,
                        currentDay = currentDay
                    )
                }

                item {
                    val day = plan.firstOrNull { it.dayNumber == currentDay }
                    if (day != null) {
                        TodayCard(
                            day = day,
                            strings = strings,
                            booksById = booksById,
                            language = language,
                            isComplete = day.isComplete(state),
                            chaptersRead = day.chaptersRead(state),
                            chaptersTotal = day.chapterKeys().size,
                            onOpenDay = onOpenDay
                        )
                    }
                }

                items(plan, key = { it.dayNumber }) { day ->
                    DayRow(
                        day = day,
                        date = state.startDate.plus(day.dayNumber - 1, DateTimeUnit.DAY),
                        strings = strings,
                        booksById = booksById,
                        language = language,
                        isComplete = day.isComplete(state),
                        completedOn = day.completedAt(state)
                            ?.toLocalDateTime(TimeZone.currentSystemDefault())?.date,
                        chaptersRead = day.chaptersRead(state),
                        chaptersTotal = day.chapterKeys().size,
                        isToday = day.dayNumber == currentDay,
                        isPast = day.dayNumber < currentDay,
                        onOpenDay = onOpenDay
                    )
                }
            }
        }
    }
}

@Composable
private fun SignInPrompt(strings: BibleStrings, onSignIn: () -> Unit) {
    ACICard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(ACISpacing.md)
    ) {
        Column(modifier = Modifier.padding(ACISpacing.md)) {
            Text(strings.readingPlanTitle, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(ACISpacing.xs))
            Text(
                strings.readingPlanBlurb,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(ACISpacing.md))
            Text(strings.signInToTrack, style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(ACISpacing.xxs))
            Text(
                strings.signInToTrackBlurb,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(ACISpacing.sm))
            ACIButton(text = strings.signInCta, onClick = onSignIn, type = ACIButtonType.Primary)
        }
    }
}

/** Read-only row for visitors — the readings, with no completion state to show. */
@Composable
private fun PlanDayRow(
    day: ReadingPlanDay,
    strings: BibleStrings,
    booksById: Map<String, BibleBook>,
    language: Language,
    onOpenDay: (Int) -> Unit
) {
    ACICard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ACISpacing.md, vertical = ACISpacing.xxs)
            .clickable {
                onOpenDay(day.dayNumber)
            }
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(ACISpacing.md)) {
            Text(
                strings.dayOf(day.dayNumber, READING_PLAN_LENGTH_DAYS),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(day.label(booksById, language), style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
private fun PlanIntro(strings: BibleStrings, onStart: () -> Unit) {
    ACICard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(ACISpacing.md)
    ) {
        Column(modifier = Modifier.padding(ACISpacing.md)) {
            Text(strings.readingPlanTitle, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(ACISpacing.xs))
            Text(
                strings.readingPlanBlurb,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(ACISpacing.md))
            ACIButton(text = strings.startPlan, onClick = onStart, type = ACIButtonType.Primary)
        }
    }
}

@Composable
private fun ProgressCard(
    strings: BibleStrings,
    completed: Int,
    behind: Int,
    currentDay: Int
) {
    ACICard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ACISpacing.md, vertical = ACISpacing.xs)
    ) {
        Column(modifier = Modifier.padding(ACISpacing.md)) {
            Text(
                strings.dayOf(currentDay, READING_PLAN_LENGTH_DAYS),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(ACISpacing.xs))
            LinearProgressIndicator(
                progress = { completed.toFloat() / READING_PLAN_LENGTH_DAYS },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(ACISpacing.xs))
            Text(
                strings.daysCompleted(completed, READING_PLAN_LENGTH_DAYS),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = when {
                    completed >= READING_PLAN_LENGTH_DAYS -> strings.planFinished
                    behind > 0 -> strings.daysBehind(behind)
                    else -> strings.onTrack
                },
                style = MaterialTheme.typography.labelLarge,
                color = if (behind > 0) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(ACISpacing.xxs))
            Text(
                strings.autoProgressHint,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun TodayCard(
    day: ReadingPlanDay,
    strings: BibleStrings,
    booksById: Map<String, BibleBook>,
    language: Language,
    isComplete: Boolean,
    chaptersRead: Int,
    chaptersTotal: Int,
    onOpenDay: (Int) -> Unit
) {
    ACICard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ACISpacing.md, vertical = ACISpacing.xs)
            .clickable {
                onOpenDay(day.dayNumber)
            }
    ) {
        Column(modifier = Modifier.padding(ACISpacing.md)) {
            Text(
                strings.todaysReading,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(ACISpacing.xxs))
            Text(day.label(booksById, language), style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(ACISpacing.xxs))
            LinearProgressIndicator(
                progress = { chaptersRead.toFloat() / chaptersTotal.coerceAtLeast(1) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(ACISpacing.xxs))
            Text(
                if (isComplete) strings.completedLabel else strings.chaptersReadOf(chaptersRead, chaptersTotal),
                style = MaterialTheme.typography.labelMedium,
                color = if (isComplete) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun DayRow(
    day: ReadingPlanDay,
    date: LocalDate,
    strings: BibleStrings,
    booksById: Map<String, BibleBook>,
    language: Language,
    isComplete: Boolean,
    completedOn: LocalDate?,
    chaptersRead: Int,
    chaptersTotal: Int,
    isToday: Boolean,
    isPast: Boolean,
    onOpenDay: (Int) -> Unit
) {
    val statusLabel = when {
        isComplete && completedOn != null -> strings.completedOn("$completedOn")
        isComplete -> strings.completedLabel
        chaptersRead > 0 -> strings.chaptersReadOf(chaptersRead, chaptersTotal)
        isToday -> strings.todayLabel
        isPast -> strings.missedLabel
        else -> "$date"
    }

    ACICard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ACISpacing.md, vertical = ACISpacing.xxs)
            .clickable {
                onOpenDay(day.dayNumber)
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(ACISpacing.md),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        strings.dayOf(day.dayNumber, READING_PLAN_LENGTH_DAYS),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    if (isToday) {
                        Spacer(modifier = Modifier.width(ACISpacing.xs))
                        Surface(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(
                                strings.todayLabel,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
                Text(day.label(booksById, language), style = MaterialTheme.typography.bodyLarge)
                Text(
                    statusLabel,
                    style = MaterialTheme.typography.labelSmall,
                    color = when {
                        isComplete -> MaterialTheme.colorScheme.primary
                        isPast -> MaterialTheme.colorScheme.error
                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            }
            Icon(
                imageVector = if (isComplete) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                contentDescription = if (isComplete) strings.completedLabel else strings.missedLabel,
                tint = if (isComplete) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
