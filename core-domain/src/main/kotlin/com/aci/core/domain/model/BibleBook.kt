package com.aci.core.domain.model

import com.aci.core.domain.enum.Language
import com.aci.core.domain.enum.Testament
import kotlinx.serialization.Serializable

@Serializable
data class BibleBook(
    val id: String,
    val testament: Testament = Testament.OLD,
    val order: Int = 0,
    val nameEn: String = "",
    val nameTa: String = "",
    val nameTe: String = "",
    val numChapters: Int = 0
)

fun BibleBook.nameFor(language: Language): String = when (language) {
    Language.TAMIL -> nameTa.ifBlank { nameEn }
    Language.TELUGU -> nameTe.ifBlank { nameEn }
    Language.ENGLISH -> nameEn
}
