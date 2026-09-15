package com.aci.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class WorshipTeamMember(
    val id: String,
    val branchId: String,
    val userId: String,
    val roleName: String = "",
    val instrument: String = "",
    val active: Boolean = true
)
