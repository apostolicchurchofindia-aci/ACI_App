package com.aci.core.domain.model

import com.aci.core.domain.enum.GivingCategory
import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class Donation(
    val id: String,
    val userId: String,
    val branchId: String,
    val category: GivingCategory = GivingCategory.OFFERING,
    val amountCents: Int = 0,
    val currency: String = "INR",
    val method: String = "",
    val isRecurring: Boolean = false,
    val recurringPeriod: String = "",
    val receiptNumber: String = "",
    val donatedAt: Instant = Instant.DISTANT_PAST
)
