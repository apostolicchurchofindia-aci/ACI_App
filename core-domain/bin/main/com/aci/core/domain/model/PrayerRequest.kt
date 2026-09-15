package com.aci.core.domain.model

import com.aci.core.domain.enum.ContentStatus
import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class PrayerRequest(
    val id: String,
    val userId: String,
    val branchId: String,
    val text: String,
    val isAnonymous: Boolean = false,
    val isPrivate: Boolean = false,
    val prayedCount: Int = 0,
    val answered: Boolean = false,
    val answeredAt: Instant? = null,
    val createdAt: Instant = Instant.DISTANT_PAST,
    val status: ContentStatus = ContentStatus.DRAFT
)
