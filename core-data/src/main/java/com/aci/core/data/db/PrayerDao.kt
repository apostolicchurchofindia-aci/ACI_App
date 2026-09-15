package com.aci.core.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PrayerDao {
    @Query("SELECT * FROM prayer_requests WHERE isPrivate = 0 AND answered = 0 ORDER BY createdAtEpochMillis DESC")
    fun observeActiveRequests(): Flow<List<PrayerRequestEntity>>

    @Query("SELECT * FROM prayer_requests WHERE isPrivate = 0 AND answered = 1 ORDER BY answeredAtEpochMillis DESC")
    fun observeAnsweredRequests(): Flow<List<PrayerRequestEntity>>

    @Query("SELECT * FROM prayer_requests WHERE userId = :userId ORDER BY createdAtEpochMillis DESC")
    fun observeMyRequests(userId: String): Flow<List<PrayerRequestEntity>>

    @Query("SELECT * FROM testimonies WHERE isPublic = 1 AND approved = 1 ORDER BY createdAtEpochMillis DESC")
    fun observePublicTestimonies(): Flow<List<TestimonyEntity>>

    @Query("SELECT * FROM testimonies WHERE isPublic = 0 OR approved = 0 ORDER BY createdAtEpochMillis DESC")
    fun observePendingOrPrivateTestimonies(): Flow<List<TestimonyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrayerRequest(request: PrayerRequestEntity)

    @Query("UPDATE prayer_requests SET prayedCount = prayedCount + 1 WHERE id = :id")
    suspend fun incrementPrayedCount(id: String)

    @Query("UPDATE prayer_requests SET answered = 1, answeredAtEpochMillis = :answeredAt WHERE id = :id")
    suspend fun markAnswered(id: String, answeredAt: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTestimony(testimony: TestimonyEntity)
}
