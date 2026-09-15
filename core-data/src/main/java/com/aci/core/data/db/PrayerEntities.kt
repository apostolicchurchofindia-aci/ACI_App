package com.aci.core.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prayer_requests")
data class PrayerRequestEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val branchId: String,
    val text: String,
    val isAnonymous: Boolean,
    val isPrivate: Boolean,
    val prayedCount: Int,
    val answered: Boolean,
    val answeredAtEpochMillis: Long?,
    val createdAtEpochMillis: Long,
    val status: String
)

@Entity(tableName = "testimonies")
data class TestimonyEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val branchId: String,
    val title: String,
    val description: String,
    val mediaUrlsJson: String,
    val isPublic: Boolean,
    val approved: Boolean,
    val createdAtEpochMillis: Long
)
