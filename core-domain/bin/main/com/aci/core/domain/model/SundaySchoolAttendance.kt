package com.aci.core.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate

@Serializable
data class SundaySchoolAttendance(
    val classId: String,
    val userId: String,
    val lessonDate: LocalDate,
    val present: Boolean = false
)
