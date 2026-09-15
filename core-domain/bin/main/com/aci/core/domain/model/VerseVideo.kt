package com.aci.core.domain.model

import com.aci.core.domain.enum.Language
import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

@Serializable
data class VerseVideo(
    val id: String,
    val dailyVerseDate: LocalDate,
    val youtubeUrl: String = "",
    val youtubeVideoId: String = "",
    val title: String = "",
    val thumbnail: String = "",
    val description: String = "",
    val language: Language = Language.ENGLISH,
    val bibleReference: String = "",
    val publishedAt: Instant? = null
)
