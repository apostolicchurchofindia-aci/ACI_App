package com.aci.core.domain.model

import com.aci.core.domain.enum.Role
import kotlinx.serialization.Serializable

@Serializable
data class RolePermission(
    val role: Role,
    val permissionKey: String
)
