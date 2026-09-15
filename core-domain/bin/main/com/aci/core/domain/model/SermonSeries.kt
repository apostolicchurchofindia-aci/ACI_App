package com.aci.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SermonSeries(
    val id: String,
    val name: String,
    val description: String = "",
    val coverUrl: String = "",
    val branchId: String = ""
)
