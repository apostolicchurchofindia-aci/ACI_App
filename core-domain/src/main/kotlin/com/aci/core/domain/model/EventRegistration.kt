package com.aci.core.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class EventRegistration(
    val id: String,
    val eventId: String,
    val userId: String,
    val name: String = "",
    val phone: String = "",
    val seats: Int = 1,
    val registeredAt: Instant = Instant.DISTANT_PAST,
    val checkedInAt: Instant? = null,
    val qrcodePayload: String = ""
)
