package com.aci.core.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class Group(
    val id: String,
    val name: String,
    val description: String = "",
    val coverUrl: String = "",
    val branchId: String = "",
    val category: String = "",
    val isPrivate: Boolean = false,
    val createdAt: Instant = Instant.DISTANT_PAST
)
