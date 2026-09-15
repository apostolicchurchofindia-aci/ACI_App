package com.aci.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Permission(
    val key: String,
    val name: String,
    val desc: String = ""
)
