package com.aci.core.domain.model

import com.aci.core.domain.enum.Language
import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class Sermon(
    val id: String,
    val title: String,
    val seriesId: String = "",
    val speaker: String = "",
    val topic: String = "",
    val description: String = "",
    val bibleRefsJson: String = "",
    val thumbnailUrl: String = "",
    val videoUrl: String = "",
    val audioUrl: String = "",
    val transcriptText: String = "",
    val notesPdfUrl: String = "",
    val preachedAt: Instant? = null,
    val language: Language = Language.ENGLISH,
    val durationSeconds: Int = 0
)
