package com.aci.core.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate

@Serializable
data class Rehearsal(
    val id: String,
    val branchId: String,
    val date: LocalDate? = null,
    val time: String = "",
    val location: String = "",
    val notes: String = "",
    val attendanceJson: String = ""
)
