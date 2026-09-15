package com.aci.core.domain.model

import com.aci.core.domain.enum.Language
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

/** A contiguous chapter range within one book, e.g. Genesis 1–3. */
@Serializable
data class PlanReading(
    val bookId: String,
    val startChapter: Int,
    val endChapter: Int
)

@Serializable
data class ReadingPlanDay(
    val dayNumber: Int,
    val readings: List<PlanReading> = emptyList()
)

/**
 * A member's progress through the plan. [startDate] is the day they pressed Start, so "today's
 * reading" is always relative to their own start rather than the calendar year.
 */
data class ReadingPlanState(
    val startDate: LocalDate,
    /** Chapters read through in the reader, keyed by [chapterKey], valued by when they were read. */
    val readChapters: Map<String, Instant> = emptyMap()
)

fun chapterKey(bookId: String, chapter: Int): String = "$bookId-$chapter"

/** Every chapter this day covers, expanded from its ranges. */
fun ReadingPlanDay.chapterKeys(): List<String> =
    readings.flatMap { r -> (r.startChapter..r.endChapter).map { chapterKey(r.bookId, it) } }

/**
 * A day is done only once every chapter it covers has actually been read — there is no way to
 * declare a day complete without reading it.
 */
fun ReadingPlanDay.isComplete(state: ReadingPlanState): Boolean =
    chapterKeys().all { it in state.readChapters }

/** When the last of this day's chapters was read, or null while the day is unfinished. */
fun ReadingPlanDay.completedAt(state: ReadingPlanState): Instant? =
    chapterKeys().map { state.readChapters[it] ?: return null }.maxOrNull()

/** How many of this day's chapters are read — drives the "2 of 3 chapters" progress line. */
fun ReadingPlanDay.chaptersRead(state: ReadingPlanState): Int =
    chapterKeys().count { it in state.readChapters }

fun List<ReadingPlanDay>.completedCount(state: ReadingPlanState): Int =
    count { it.isComplete(state) }

const val READING_PLAN_LENGTH_DAYS = 365

fun PlanReading.label(book: BibleBook?, language: Language): String {
    val name = book?.nameFor(language) ?: bookId
    return if (startChapter == endChapter) "$name $startChapter" else "$name $startChapter–$endChapter"
}

fun ReadingPlanDay.label(booksById: Map<String, BibleBook>, language: Language): String =
    readings.joinToString(", ") { it.label(booksById[it.bookId], language) }

/**
 * Which plan day the member is on today — 1-based, clamped to the plan length. Note this tracks
 * elapsed calendar days, not days completed: falling behind shows up as uncompleted earlier days
 * rather than by freezing this number.
 */
fun ReadingPlanState.currentDay(today: LocalDate): Int {
    val elapsed = today.toEpochDays() - startDate.toEpochDays()
    return (elapsed + 1).coerceIn(1, READING_PLAN_LENGTH_DAYS)
}
