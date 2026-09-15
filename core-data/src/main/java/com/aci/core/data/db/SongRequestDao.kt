package com.aci.core.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SongRequestDao {
    @Query("SELECT * FROM song_requests WHERE status = 'PENDING' ORDER BY createdAtEpochMillis ASC")
    fun observePending(): Flow<List<SongRequestEntity>>

    @Query("SELECT * FROM song_requests ORDER BY createdAtEpochMillis DESC")
    fun observeAll(): Flow<List<SongRequestEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(request: SongRequestEntity)

    @Query("SELECT * FROM song_requests WHERE id = :id LIMIT 1")
    suspend fun getById(id: String): SongRequestEntity?

    @Query("UPDATE song_requests SET status = :status, reviewedAtEpochMillis = :reviewedAt, reviewNote = :reviewNote WHERE id = :id")
    suspend fun updateStatus(id: String, status: String, reviewedAt: Long, reviewNote: String)
}
