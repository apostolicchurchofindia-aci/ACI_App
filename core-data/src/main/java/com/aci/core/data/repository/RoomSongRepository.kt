package com.aci.core.data.repository

import com.aci.core.data.db.SongDao
import com.aci.core.domain.model.Song
import com.aci.core.domain.model.SongLyricSection
import com.aci.core.domain.model.SongMedia
import com.aci.core.domain.repository.SongRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomSongRepository(private val dao: SongDao) : SongRepository {

    override fun observeSongs(): Flow<List<Song>> =
        dao.observeSongs().map { entities -> entities.map { it.toDomain() } }

    override fun observeSong(id: String): Flow<Song?> =
        dao.observeSong(id).map { it?.toDomain() }

    override fun observeLyrics(songId: String): Flow<List<SongLyricSection>> =
        dao.observeLyrics(songId).map { entities -> entities.map { it.toDomain() } }

    override fun observeMedia(songId: String): Flow<SongMedia?> =
        dao.observeMedia(songId).map { it?.toDomain() }

    override suspend fun isEmpty(): Boolean = dao.countSongs() == 0

    override suspend fun upsertAll(songs: List<Song>, lyrics: List<SongLyricSection>, media: List<SongMedia>) {
        dao.insertSongs(songs.map { it.toEntity() })
        dao.insertLyrics(lyrics.map { it.toEntity() })
        dao.insertMedia(media.map { it.toEntity() })
    }
}
