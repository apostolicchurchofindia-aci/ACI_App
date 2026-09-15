package com.aci.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SongMedia(
    val songId: String,
    val audioUrl: String = "",
    val youtubeUrl: String = "",
    val pdfUrl: String = "",
    val presentationUrl: String = "",
    val practiceUrl: String = ""
)
