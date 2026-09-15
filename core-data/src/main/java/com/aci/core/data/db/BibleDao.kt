package com.aci.core.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BibleDao {
    @Query("SELECT * FROM bible_books ORDER BY `order`")
    fun observeBooks(): Flow<List<BibleBookEntity>>

    @Query("SELECT * FROM bible_verses WHERE bookId = :bookId AND chapter = :chapter ORDER BY verseNumber")
    fun observeVerses(bookId: String, chapter: Int): Flow<List<BibleVerseEntity>>

    @Query(
        "SELECT * FROM bible_verses WHERE bookId = :bookId " +
            "AND chapter BETWEEN :startChapter AND :endChapter ORDER BY chapter, verseNumber"
    )
    fun observeVerseRange(bookId: String, startChapter: Int, endChapter: Int): Flow<List<BibleVerseEntity>>

    @Query("SELECT * FROM bible_verses WHERE id = :verseId LIMIT 1")
    fun observeVerse(verseId: String): Flow<BibleVerseEntity?>

    @Query("SELECT * FROM bible_verses WHERE textEn LIKE '%' || :query || '%' LIMIT 100")
    fun searchVerses(query: String): Flow<List<BibleVerseEntity>>

    @Query("SELECT * FROM verse_bookmarks WHERE userId = :userId ORDER BY createdAtEpochMillis DESC")
    fun observeBookmarks(userId: String): Flow<List<VerseBookmarkEntity>>

    @Query(
        """SELECT v.* FROM bible_verses v
           INNER JOIN verse_bookmarks b ON b.verseId = v.id
           WHERE b.userId = :userId ORDER BY b.createdAtEpochMillis DESC"""
    )
    fun observeBookmarkedVerses(userId: String): Flow<List<BibleVerseEntity>>

    @Query("SELECT * FROM verse_notes WHERE userId = :userId ORDER BY createdAtEpochMillis DESC")
    fun observeNotes(userId: String): Flow<List<VerseNoteEntity>>

    @Query("SELECT * FROM verse_notes WHERE userId = :userId AND verseId = :verseId ORDER BY createdAtEpochMillis DESC")
    fun observeNotesForVerse(userId: String, verseId: String): Flow<List<VerseNoteEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM verse_bookmarks WHERE userId = :userId AND verseId = :verseId)")
    suspend fun isBookmarked(userId: String, verseId: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: VerseBookmarkEntity)

    @Query("DELETE FROM verse_bookmarks WHERE userId = :userId AND verseId = :verseId")
    suspend fun deleteBookmark(userId: String, verseId: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: VerseNoteEntity)

    @Query("DELETE FROM verse_notes WHERE userId = :userId AND verseId = :verseId AND createdAtEpochMillis = :createdAtEpochMillis")
    suspend fun deleteNote(userId: String, verseId: String, createdAtEpochMillis: Long)

    @Query("SELECT COUNT(*) FROM bible_books")
    suspend fun countBooks(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: List<BibleBookEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVerses(verses: List<BibleVerseEntity>)
}
