package com.aci.core.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reading_plan_state")
data class ReadingPlanStateEntity(
    @PrimaryKey val userId: String,
    val startDateEpochDay: Long
)

/**
 * One row per chapter the member has actually read through in the reader. Plan days complete
 * themselves once every chapter they cover appears here, so progress reflects reading rather
 * than a button press.
 */
@Entity(tableName = "chapter_reads", primaryKeys = ["userId", "bookId", "chapter"])
data class ChapterReadEntity(
    val userId: String,
    val bookId: String,
    val chapter: Int,
    val readAtEpochMillis: Long
)
