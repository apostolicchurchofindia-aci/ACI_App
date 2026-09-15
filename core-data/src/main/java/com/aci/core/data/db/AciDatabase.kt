package com.aci.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        SongEntity::class, SongLyricSectionEntity::class, SongMediaEntity::class,
        BibleBookEntity::class, BibleVerseEntity::class, VerseBookmarkEntity::class, VerseNoteEntity::class,
        PrayerRequestEntity::class, TestimonyEntity::class,
        EventEntity::class, EventRegistrationEntity::class,
        SongRequestEntity::class,
        UserEntity::class,
        ReadingPlanStateEntity::class, ChapterReadEntity::class
    ],
    version = 9,
    exportSchema = false
)
abstract class AciDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
    abstract fun bibleDao(): BibleDao
    abstract fun prayerDao(): PrayerDao
    abstract fun eventDao(): EventDao
    abstract fun songRequestDao(): SongRequestDao
    abstract fun userDao(): UserDao
    abstract fun readingPlanDao(): ReadingPlanDao

    companion object {
        const val DATABASE_NAME = "aci_church.db"
    }
}
