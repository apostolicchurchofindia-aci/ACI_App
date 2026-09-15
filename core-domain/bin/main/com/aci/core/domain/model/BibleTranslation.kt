package com.aci.core.domain.model

import com.aci.core.domain.enum.Language
import kotlinx.serialization.Serializable

@Serializable
data class BibleTranslation(
    val id: String,
    val code: String,
    val nameEn: String,
    val language: Language = Language.ENGLISH
)
