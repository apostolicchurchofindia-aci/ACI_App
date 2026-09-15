package com.aci.core.domain.model

import com.aci.core.domain.enum.Language
import kotlinx.serialization.Serializable

@Serializable
data class SongLyricSection(
    val id: String,
    val songId: String,
    val language: Language = Language.ENGLISH,
    val sectionLabel: String = "",
    val lyrics: String = "",
    /** Romanized transliteration of [lyrics], shown under the native script like ChristianLyricz-style songbooks. */
    val translitLyrics: String = "",
    /** [lyrics] transliterated phonetically into Tamil script (same approach as [translitLyrics]'s English romanization, not a meaning translation). */
    val translationTa: String = "",
    val orderIndex: Int = 0
)
