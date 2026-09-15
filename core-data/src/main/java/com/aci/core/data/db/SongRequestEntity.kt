package com.aci.core.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "song_requests")
data class SongRequestEntity(
    @PrimaryKey val id: String,
    val titleEn: String,
    val titleNative: String,
    val language: String,
    val notes: String,
    val requestedByName: String,
    val status: String,
    val createdAtEpochMillis: Long,
    val reviewedAtEpochMillis: Long?,
    val reviewNote: String
)
