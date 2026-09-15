package com.aci.core.domain.model

import com.aci.core.domain.enum.Language
import kotlinx.serialization.Serializable

@Serializable
data class SongTranslation(
    val songId: String,
    val language: Language = Language.ENGLISH,
    val title: String = ""
)
