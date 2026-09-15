package com.aci.core.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class GroupComment(
    val id: String,
    val postId: String,
    val userId: String,
    val text: String = "",
    val createdAt: Instant = Instant.DISTANT_PAST
)
