package com.aci.core.domain.model

import com.aci.core.domain.enum.Language
import kotlinx.serialization.Serializable

@Serializable
data class BibleVerseText(
    val en: String = "",
    val ta: String = "",
    val te: String = ""
)

/** Picks the field for [language], falling back to English wherever a translation is missing. */
fun BibleVerseText.forLanguage(language: Language): String = when (language) {
    Language.TAMIL -> ta.ifBlank { en }
    Language.TELUGU -> te.ifBlank { en }
    Language.ENGLISH -> en
}

/**
 * Which translation the reader is actually looking at. The three languages ship different
 * translations, so a single stored code (the English "KJV") would misattribute the Tamil and
 * Telugu text — and BSI's licence makes correct attribution a condition of use, not a nicety.
 * See `BibleSeeder` for full provenance; the exact wording BSI requires still needs confirming
 * with them and replacing here.
 */
fun translationCodeFor(language: Language): String = when (language) {
    Language.ENGLISH -> "KJV"
    Language.TAMIL, Language.TELUGU -> "BSI O.V."
}
