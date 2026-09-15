package com.aci.core.domain.model

import com.aci.core.domain.enum.ContentStatus
import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate

@Serializable
data class WorshipSetlist(
    val id: String,
    val name: String = "",
    val date: LocalDate? = null,
    val branchId: String = "",
    val createdByUserId: String = "",
    val status: ContentStatus = ContentStatus.DRAFT
)
