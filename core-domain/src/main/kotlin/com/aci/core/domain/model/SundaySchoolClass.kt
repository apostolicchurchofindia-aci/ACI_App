package com.aci.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SundaySchoolClass(
    val id: String,
    val name: String,
    val branchId: String,
    val teacherUserId: String = "",
    val academicYear: String = ""
)
