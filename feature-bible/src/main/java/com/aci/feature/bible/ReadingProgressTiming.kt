package com.aci.feature.bible

/**
 * Shared policy for when reading "counts" — used by both the chapter reader and the reading-plan
 * day reader so a chapter is treated the same whichever way it was opened.
 */

/** A chapter never counts as read before this, however fast the member scrolls. */
internal const val MIN_DWELL_MILLIS = 8_000L

private const val MILLIS_PER_VERSE = 4_000L
private const val MAX_READ_MILLIS = 240_000L

internal fun estimatedReadMillis(verseCount: Int): Long =
    (verseCount * MILLIS_PER_VERSE).coerceIn(MIN_DWELL_MILLIS, MAX_READ_MILLIS)
