package com.aci.core.data.repository

import com.aci.core.data.db.SongDao
import com.aci.core.data.db.SongRequestDao
import com.aci.core.domain.model.Song
import com.aci.core.domain.model.SongRequest
import com.aci.core.domain.model.SongRequestStatus
import com.aci.core.domain.repository.SongRequestRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class RoomSongRequestRepository(
    private val requestDao: SongRequestDao,
    private val songDao: SongDao
) : SongRequestRepository {

    override fun observePending(): Flow<List<SongRequest>> =
        requestDao.observePending().map { it.map { e -> e.toDomain() } }

    override fun observeAll(): Flow<List<SongRequest>> =
        requestDao.observeAll().map { it.map { e -> e.toDomain() } }

    override suspend fun submit(request: SongRequest) {
        requestDao.insert(request.toEntity())
    }

    override suspend fun approve(id: String, reviewNote: String) {
        val request = requestDao.getById(id) ?: return
        val now = Clock.System.now()

        // Metadata-only: title/language/notes come from the member's request, but no lyrics
        // are copied in automatically — an admin adds those separately once rights are
        // confirmed, same as every other song in this catalog.
        val song = Song(
            id = "song-request-${request.id}",
            titleEn = request.titleEn,
            titleTa = if (request.language.equals("Tamil", ignoreCase = true)) request.titleNative else "",
            titleTe = if (request.language.equals("Telugu", ignoreCase = true)) request.titleNative else null,
            language = request.language,
            hasLyrics = false,
            createdAt = now
        )
        songDao.insertSongs(listOf(song.toEntity()))

        requestDao.updateStatus(id, SongRequestStatus.APPROVED.name, now.toEpochMilliseconds(), reviewNote)
    }

    override suspend fun reject(id: String, reviewNote: String) {
        requestDao.updateStatus(id, SongRequestStatus.REJECTED.name, Clock.System.now().toEpochMilliseconds(), reviewNote)
    }
}
