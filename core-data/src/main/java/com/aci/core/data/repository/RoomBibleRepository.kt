package com.aci.core.data.repository

import com.aci.core.data.db.BibleDao
import com.aci.core.data.db.VerseBookmarkEntity
import com.aci.core.domain.model.BibleBook
import com.aci.core.domain.model.BibleVerse
import com.aci.core.domain.model.VerseBookmark
import com.aci.core.domain.model.VerseNote
import com.aci.core.domain.repository.BibleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class RoomBibleRepository(private val dao: BibleDao) : BibleRepository {

    override fun observeBooks(): Flow<List<BibleBook>> =
        dao.observeBooks().map { it.map { e -> e.toDomain() } }

    override fun observeVerses(bookId: String, chapter: Int): Flow<List<BibleVerse>> =
        dao.observeVerses(bookId, chapter).map { it.map { e -> e.toDomain() } }

    override fun observeVerseRange(bookId: String, startChapter: Int, endChapter: Int): Flow<List<BibleVerse>> =
        dao.observeVerseRange(bookId, startChapter, endChapter).map { it.map { e -> e.toDomain() } }

    override fun observeVerse(verseId: String): Flow<BibleVerse?> =
        dao.observeVerse(verseId).map { it?.toDomain() }

    override fun searchVerses(query: String): Flow<List<BibleVerse>> =
        dao.searchVerses(query).map { it.map { e -> e.toDomain() } }

    override fun observeBookmarks(userId: String): Flow<List<VerseBookmark>> =
        dao.observeBookmarks(userId).map { it.map { e -> e.toDomain() } }

    override fun observeBookmarkedVerses(userId: String): Flow<List<BibleVerse>> =
        dao.observeBookmarkedVerses(userId).map { it.map { e -> e.toDomain() } }

    override fun observeNotes(userId: String): Flow<List<VerseNote>> =
        dao.observeNotes(userId).map { it.map { e -> e.toDomain() } }

    override fun observeNotesForVerse(userId: String, verseId: String): Flow<List<VerseNote>> =
        dao.observeNotesForVerse(userId, verseId).map { it.map { e -> e.toDomain() } }

    override suspend fun toggleBookmark(userId: String, verseId: String) {
        if (dao.isBookmarked(userId, verseId)) {
            dao.deleteBookmark(userId, verseId)
        } else {
            dao.insertBookmark(
                VerseBookmarkEntity(userId, verseId, Clock.System.now().toEpochMilliseconds())
            )
        }
    }

    override suspend fun addNote(note: VerseNote) {
        dao.insertNote(note.toEntity())
    }

    override suspend fun deleteNote(userId: String, verseId: String, createdAtEpochMillis: Long) {
        dao.deleteNote(userId, verseId, createdAtEpochMillis)
    }

    override suspend fun isEmpty(): Boolean = dao.countBooks() == 0

    override suspend fun upsertBooks(books: List<BibleBook>) {
        dao.insertBooks(books.map { it.toEntity() })
    }

    override suspend fun upsertVerses(verses: List<BibleVerse>) {
        // Chunked to keep each transaction/statement batch reasonable for a 31k-row bulk insert.
        verses.chunked(2000).forEach { chunk -> dao.insertVerses(chunk.map { it.toEntity() }) }
    }
}
