package com.aci.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class BibleVerse(
    val id: String,
    val bookId: String,
    val chapter: Int = 0,
    val verseNumber: Int = 0,
    val text: BibleVerseText = BibleVerseText()
)
