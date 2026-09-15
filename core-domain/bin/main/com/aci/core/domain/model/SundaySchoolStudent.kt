package com.aci.core.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class SundaySchoolStudent(
    val classId: String,
    val userId: String,
    val enrolledAt: Instant = Instant.DISTANT_PAST,
    val attendanceCount: Int = 0,
    val totalPoints: Int = 0
)
