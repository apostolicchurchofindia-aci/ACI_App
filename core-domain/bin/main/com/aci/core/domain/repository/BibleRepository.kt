package com.aci.core.domain.repository

import com.aci.core.domain.model.BibleBook
import com.aci.core.domain.model.BibleVerse
import com.aci.core.domain.model.VerseBookmark
import com.aci.core.domain.model.VerseNote
import kotlinx.coroutines.flow.Flow

interface BibleRepository {
    fun observeBooks(): Flow<List<BibleBook>>
    fun observeVerses(bookId: String, chapter: Int): Flow<List<BibleVerse>>

    /** Verses across a chapter range, ordered — used to load one reading-plan day in full. */
    fun observeVerseRange(bookId: String, startChapter: Int, endChapter: Int): Flow<List<BibleVerse>>
    fun observeVerse(verseId: String): Flow<BibleVerse?>
    fun searchVerses(query: String): Flow<List<BibleVerse>>
    fun observeBookmarks(userId: String): Flow<List<VerseBookmark>>
    fun observeBookmarkedVerses(userId: String): Flow<List<BibleVerse>>
    fun observeNotes(userId: String): Flow<List<VerseNote>>
    fun observeNotesForVerse(userId: String, verseId: String): Flow<List<VerseNote>>

    suspend fun toggleBookmark(userId: String, verseId: String)
    suspend fun addNote(note: VerseNote)
    suspend fun deleteNote(userId: String, verseId: String, createdAtEpochMillis: Long)

    suspend fun isEmpty(): Boolean
    suspend fun upsertBooks(books: List<BibleBook>)
    suspend fun upsertVerses(verses: List<BibleVerse>)
}
