package com.aci.core.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate

@Serializable
data class MemoryVerse(
    val id: String,
    val reference: String = "",
    val text: BibleVerseText = BibleVerseText(),
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val branchId: String = "",
    val classId: String? = null
)
