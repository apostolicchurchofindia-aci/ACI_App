package com.aci.core.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class Branch(
    val id: String,
    val name: String,
    val address: String = "",
    val city: String = "",
    val state: String = "",
    val country: String = "",
    val pincode: String = "",
    val lat: Double? = null,
    val lng: Double? = null,
    val pastorName: String = "",
    val contactEmail: String = "",
    val contactPhone: String = "",
    val website: String = "",
    val serviceTimesJson: String = "",
    val establishedAt: Instant? = null
)
