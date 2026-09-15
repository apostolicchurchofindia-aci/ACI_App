package com.aci.core.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey val id: String,
    val titleEn: String,
    val titleTa: String,
    val titleTe: String?,
    val titleTeTranslit: String,
    val titleTaTranslit: String,
    val language: String,
    val category: String,
    val key: String?,
    val bpm: Int?,
    val composer: String,
    val artist: String,
    val artworkUrl: String,
    val hasLyrics: Boolean,
    val hasChords: Boolean,
    val hasAudio: Boolean,
    val songbookName: String,
    val songbookNumber: Int?,
    val createdAtEpochMillis: Long
)

@Entity(tableName = "song_lyric_sections")
data class SongLyricSectionEntity(
    @PrimaryKey val id: String,
    val songId: String,
    val language: String,
    val sectionLabel: String,
    val lyrics: String,
    val translitLyrics: String,
    val translationTa: String = "",
    val orderIndex: Int
)

@Entity(tableName = "song_media")
data class SongMediaEntity(
    @PrimaryKey val songId: String,
    val audioUrl: String,
    val youtubeUrl: String,
    val pdfUrl: String,
    val presentationUrl: String,
    val practiceUrl: String
)
