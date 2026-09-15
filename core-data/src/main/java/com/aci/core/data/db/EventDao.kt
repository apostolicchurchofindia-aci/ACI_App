package com.aci.core.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Query("SELECT * FROM events ORDER BY startAtEpochMillis ASC")
    fun observeEvents(): Flow<List<EventEntity>>

    @Query("SELECT * FROM events WHERE id = :id LIMIT 1")
    fun observeEvent(id: String): Flow<EventEntity?>

    @Query("SELECT * FROM event_registrations WHERE eventId = :eventId")
    fun observeRegistrationsForEvent(eventId: String): Flow<List<EventRegistrationEntity>>

    @Query("SELECT * FROM event_registrations WHERE userId = :userId ORDER BY registeredAtEpochMillis DESC")
    fun observeMyRegistrations(userId: String): Flow<List<EventRegistrationEntity>>

    @Query("SELECT COUNT(*) FROM events")
    suspend fun countEvents(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(events: List<EventEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRegistration(registration: EventRegistrationEntity)

    @Query("UPDATE event_registrations SET checkedInAtEpochMillis = :checkedInAt WHERE id = :id")
    suspend fun checkIn(id: String, checkedInAt: Long)
}
