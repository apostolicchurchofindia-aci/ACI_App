package com.aci.core.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class QuizAttempt(
    val id: String,
    val quizId: String,
    val userId: String,
    val score: Int = 0,
    val total: Int = 0,
    val passed: Boolean = false,
    val submittedAt: Instant = Instant.DISTANT_PAST
)
