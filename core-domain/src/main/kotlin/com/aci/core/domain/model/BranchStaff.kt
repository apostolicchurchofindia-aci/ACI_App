package com.aci.core.domain.model

import com.aci.core.domain.enum.Role
import kotlinx.serialization.Serializable

@Serializable
data class BranchStaff(
    val id: String,
    val branchId: String,
    val userId: String,
    val role: Role = Role.MEMBER,
    val position: String = "",
    val joinedAt: String = ""
)
