package com.aci.core.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class MemorizationProgress(
    val userId: String,
    val memoryVerseId: String,
    val status: String = "",
    val attempts: Int = 0,
    val lastPracticedAt: Instant? = null,
    val streakDays: Int = 0
)
