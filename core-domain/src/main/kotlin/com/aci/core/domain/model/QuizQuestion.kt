package com.aci.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class QuizQuestion(
    val id: String,
    val quizId: String,
    val question: String = "",
    val optionsJson: String = "",
    val correctIndex: Int = 0,
    val explanation: String = ""
)
