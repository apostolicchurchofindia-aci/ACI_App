package com.aci.core.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class VerseBookmark(
    val userId: String,
    val verseId: String,
    val createdAt: Instant = Instant.DISTANT_PAST
)
