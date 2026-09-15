package com.aci.core.domain.model

import com.aci.core.domain.enum.Language
import kotlinx.serialization.Serializable

@Serializable
data class SongChordLine(
    val id: String,
    val songId: String,
    val language: Language = Language.ENGLISH,
    val sectionLabel: String = "",
    val lineIndex: Int = 0,
    val chords: String = "",
    val lyrics: String = ""
)
