package com.aci.core.data.repository

import com.aci.core.data.db.UserEntity
import com.aci.core.domain.enum.Role
import com.aci.core.domain.model.User
import kotlinx.datetime.Instant

/** [pinHash] is passed separately because credentials deliberately don't live on the domain model. */
fun User.toEntity(pinHash: String) = UserEntity(
    id = id,
    email = email,
    phone = phone,
    role = role.name,
    fullName = fullName,
    createdAtEpochMillis = createdAt.toEpochMilliseconds(),
    pinHash = pinHash
)

fun UserEntity.toDomain() = User(
    id = id,
    email = email,
    phone = phone,
    role = runCatching { Role.valueOf(role) }.getOrDefault(Role.MEMBER),
    fullName = fullName,
    createdAt = Instant.fromEpochMilliseconds(createdAtEpochMillis)
)
