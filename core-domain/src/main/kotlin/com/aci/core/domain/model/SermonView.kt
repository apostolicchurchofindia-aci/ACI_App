package com.aci.core.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class SermonView(
    val userId: String,
    val sermonId: String,
    val viewedAt: Instant = Instant.DISTANT_PAST
)
