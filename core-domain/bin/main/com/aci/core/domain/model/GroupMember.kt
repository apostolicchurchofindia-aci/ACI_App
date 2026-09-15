package com.aci.core.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class GroupMember(
    val groupId: String,
    val userId: String,
    val role: String = "",
    val joinedAt: Instant = Instant.DISTANT_PAST
)
