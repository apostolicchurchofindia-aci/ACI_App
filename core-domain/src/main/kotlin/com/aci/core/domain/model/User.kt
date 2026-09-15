package com.aci.core.domain.model

import com.aci.core.domain.enum.Role
import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class User(
    val id: String,
    val email: String = "",
    val phone: String = "",
    val role: Role = Role.GUEST,
    val fullName: String = "",
    val createdAt: Instant = Instant.DISTANT_PAST
)
