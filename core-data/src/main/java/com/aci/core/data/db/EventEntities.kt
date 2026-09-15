package com.aci.core.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey val id: String,
    val title: String,
    val category: String,
    val description: String,
    val imageUrl: String,
    val startAtEpochMillis: Long?,
    val endAtEpochMillis: Long?,
    val locationName: String,
    val address: String,
    val branchId: String,
    val registrationRequired: Boolean,
    val maxSeats: Int,
    val qrRequired: Boolean
)

@Entity(tableName = "event_registrations")
data class EventRegistrationEntity(
    @PrimaryKey val id: String,
    val eventId: String,
    val userId: String,
    val name: String,
    val phone: String,
    val seats: Int,
    val registeredAtEpochMillis: Long,
    val checkedInAtEpochMillis: Long?,
    val qrcodePayload: String
)
