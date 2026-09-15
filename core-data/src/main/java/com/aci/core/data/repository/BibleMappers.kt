package com.aci.core.data.repository

import com.aci.core.data.db.BibleBookEntity
import com.aci.core.data.db.BibleVerseEntity
import com.aci.core.data.db.VerseBookmarkEntity
import com.aci.core.data.db.VerseNoteEntity
import com.aci.core.domain.enum.Testament
import com.aci.core.domain.model.BibleBook
import com.aci.core.domain.model.BibleVerse
import com.aci.core.domain.model.BibleVerseText
import com.aci.core.domain.model.VerseBookmark
import com.aci.core.domain.model.VerseNote
import kotlinx.datetime.Instant

fun BibleBook.toEntity() = BibleBookEntity(
    id = id, testament = testament.name, order = order,
    nameEn = nameEn, nameTa = nameTa, nameTe = nameTe, numChapters = numChapters
)

fun BibleBookEntity.toDomain() = BibleBook(
    id = id,
    testament = runCatching { Testament.valueOf(testament) }.getOrDefault(Testament.OLD),
    order = order, nameEn = nameEn, nameTa = nameTa, nameTe = nameTe, numChapters = numChapters
)

fun BibleVerse.toEntity() = BibleVerseEntity(
    id = id, bookId = bookId, chapter = chapter, verseNumber = verseNumber,
    textEn = text.en, textTa = text.ta, textTe = text.te
)

fun BibleVerseEntity.toDomain() = BibleVerse(
    id = id, bookId = bookId, chapter = chapter, verseNumber = verseNumber,
    text = BibleVerseText(en = textEn, ta = textTa, te = textTe)
)

fun VerseBookmarkEntity.toDomain() = VerseBookmark(
    userId = userId, verseId = verseId, createdAt = Instant.fromEpochMilliseconds(createdAtEpochMillis)
)

fun VerseNoteEntity.toDomain() = VerseNote(
    userId = userId, verseId = verseId, text = text, color = color,
    createdAt = Instant.fromEpochMilliseconds(createdAtEpochMillis)
)

fun VerseNote.toEntity() = VerseNoteEntity(
    userId = userId, verseId = verseId, text = text, color = color,
    createdAtEpochMillis = createdAt.toEpochMilliseconds()
)
