package com.aci.core.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate

@Serializable
data class SundaySchoolLesson(
    val id: String,
    val classId: String,
    val title: String = "",
    val lessonDate: LocalDate? = null,
    val biblePortion: String = "",
    val teachingContent: String = "",
    val memoryVerseRef: String = "",
    val memoryVerseText: BibleVerseText = BibleVerseText()
)
