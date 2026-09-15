package com.aci.core.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate

@Serializable
data class DailyVerse(
    val date: LocalDate,
    val verseId: String = "",
    val verseRef: String = "",
    val text: BibleVerseText = BibleVerseText(),
    val translationCode: String = ""
)
