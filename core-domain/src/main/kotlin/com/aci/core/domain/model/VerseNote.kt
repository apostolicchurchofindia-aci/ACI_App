package com.aci.core.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class VerseNote(
    val userId: String,
    val verseId: String,
    val text: String = "",
    val color: String = "",
    val createdAt: Instant = Instant.DISTANT_PAST
)
