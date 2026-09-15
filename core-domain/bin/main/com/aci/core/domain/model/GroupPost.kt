package com.aci.core.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class GroupPost(
    val id: String,
    val groupId: String,
    val userId: String,
    val title: String = "",
    val content: String = "",
    val mediaUrlsJson: String = "",
    val createdAt: Instant = Instant.DISTANT_PAST
)
