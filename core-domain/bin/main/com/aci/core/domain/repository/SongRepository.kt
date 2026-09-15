package com.aci.core.domain.repository

import com.aci.core.domain.model.Song
import com.aci.core.domain.model.SongLyricSection
import com.aci.core.domain.model.SongMedia
import kotlinx.coroutines.flow.Flow

interface SongRepository {
    fun observeSongs(): Flow<List<Song>>
    fun observeSong(id: String): Flow<Song?>
    fun observeLyrics(songId: String): Flow<List<SongLyricSection>>
    fun observeMedia(songId: String): Flow<SongMedia?>
    suspend fun isEmpty(): Boolean
    suspend fun upsertAll(songs: List<Song>, lyrics: List<SongLyricSection> = emptyList(), media: List<SongMedia> = emptyList())
}
