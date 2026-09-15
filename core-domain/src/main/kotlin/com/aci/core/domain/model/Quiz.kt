package com.aci.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Quiz(
    val id: String,
    val classId: String,
    val lessonId: String,
    val title: String = "",
    val totalQuestions: Int = 0,
    val passingPercent: Int = 0
)
