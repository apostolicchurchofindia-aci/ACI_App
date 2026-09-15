package com.aci.core.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class Testimony(
    val id: String,
    val userId: String,
    val branchId: String,
    val title: String = "",
    val description: String = "",
    val mediaUrlsJson: String = "",
    val isPublic: Boolean = false,
    val approved: Boolean = false,
    val createdAt: Instant = Instant.DISTANT_PAST
)
