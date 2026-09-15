package com.aci.core.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_users")
data class UserEntity(
    @PrimaryKey val id: String,
    val email: String,
    val phone: String,
    val role: String,
    val fullName: String,
    val createdAtEpochMillis: Long,
    /** Salted PBKDF2 hash from [com.aci.core.data.auth.PinHasher] — never the PIN itself. */
    val pinHash: String
)
