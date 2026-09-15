package com.aci.core.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
    @Query("SELECT * FROM songs ORDER BY titleEn")
    fun observeSongs(): Flow<List<SongEntity>>

    @Query("SELECT * FROM songs WHERE id = :id")
    fun observeSong(id: String): Flow<SongEntity?>

    @Query("SELECT * FROM song_lyric_sections WHERE songId = :songId ORDER BY orderIndex")
    fun observeLyrics(songId: String): Flow<List<SongLyricSectionEntity>>

    @Query("SELECT * FROM song_media WHERE songId = :songId LIMIT 1")
    fun observeMedia(songId: String): Flow<SongMediaEntity?>

    @Query("SELECT COUNT(*) FROM songs")
    suspend fun countSongs(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongs(songs: List<SongEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLyrics(lyrics: List<SongLyricSectionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedia(media: List<SongMediaEntity>)
}
