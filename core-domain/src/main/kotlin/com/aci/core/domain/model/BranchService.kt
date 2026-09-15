package com.aci.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class BranchService(
    val id: String,
    val branchId: String,
    val dayOfWeek: Int = 0,
    val startTime: String = "",
    val endTime: String = "",
    val name: String = "",
    val language: String = ""
)
