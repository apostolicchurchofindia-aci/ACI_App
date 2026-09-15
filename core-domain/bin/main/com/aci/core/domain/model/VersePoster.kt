package com.aci.core.domain.model

import com.aci.core.domain.enum.ContentStatus
import com.aci.core.domain.enum.Language
import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

@Serializable
data class VersePoster(
    val id: String,
    val dailyVerseDate: LocalDate,
    val templateId: String = "",
    val backgroundUrl: String = "",
    val language: Language = Language.ENGLISH,
    val imageUrl: String = "",
    val status: ContentStatus = ContentStatus.DRAFT,
    val publishedAt: Instant? = null
)
