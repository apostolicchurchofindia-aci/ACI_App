package com.aci.core.domain.model

import com.aci.core.domain.enum.EventCategory
import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class Event(
    val id: String,
    val title: String,
    val category: EventCategory = EventCategory.OTHER,
    val description: String = "",
    val imageUrl: String = "",
    val startAt: Instant? = null,
    val endAt: Instant? = null,
    val locationName: String = "",
    val address: String = "",
    val branchId: String = "",
    val registrationRequired: Boolean = false,
    val maxSeats: Int = 0,
    val qrRequired: Boolean = false
)
