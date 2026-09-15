package com.aci.core.data.db

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "bible_books")
data class BibleBookEntity(
    @PrimaryKey val id: String,
    val testament: String,
    val order: Int,
    val nameEn: String,
    val nameTa: String,
    val nameTe: String,
    val numChapters: Int
)

@Entity(
    tableName = "bible_verses",
    indices = [Index(value = ["bookId", "chapter"])]
)
data class BibleVerseEntity(
    @PrimaryKey val id: String,
    val bookId: String,
    val chapter: Int,
    val verseNumber: Int,
    val textEn: String,
    val textTa: String,
    val textTe: String
)

@Entity(tableName = "verse_bookmarks", primaryKeys = ["userId", "verseId"])
data class VerseBookmarkEntity(
    val userId: String,
    val verseId: String,
    val createdAtEpochMillis: Long
)

@Entity(tableName = "verse_notes", primaryKeys = ["userId", "verseId", "createdAtEpochMillis"])
data class VerseNoteEntity(
    val userId: String,
    val verseId: String,
    val text: String,
    val color: String,
    val createdAtEpochMillis: Long
)
