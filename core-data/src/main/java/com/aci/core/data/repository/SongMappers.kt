package com.aci.core.data.repository

import com.aci.core.data.db.SongEntity
import com.aci.core.data.db.SongLyricSectionEntity
import com.aci.core.data.db.SongMediaEntity
import com.aci.core.domain.enum.Language
import com.aci.core.domain.enum.SongKey
import com.aci.core.domain.model.Song
import com.aci.core.domain.model.SongLyricSection
import com.aci.core.domain.model.SongMedia
import kotlinx.datetime.Instant

fun Song.toEntity() = SongEntity(
    id = id,
    titleEn = titleEn,
    titleTa = titleTa,
    titleTe = titleTe,
    titleTeTranslit = titleTeTranslit,
    titleTaTranslit = titleTaTranslit,
    language = language,
    category = category,
    key = key?.name,
    bpm = bpm,
    composer = composer,
    artist = artist,
    artworkUrl = artworkUrl,
    hasLyrics = hasLyrics,
    hasChords = hasChords,
    hasAudio = hasAudio,
    songbookName = songbookName,
    songbookNumber = songbookNumber,
    createdAtEpochMillis = createdAt.toEpochMilliseconds()
)

fun SongEntity.toDomain() = Song(
    id = id,
    titleEn = titleEn,
    titleTa = titleTa,
    titleTe = titleTe,
    titleTeTranslit = titleTeTranslit,
    titleTaTranslit = titleTaTranslit,
    language = language,
    category = category,
    key = key?.let { runCatching { SongKey.valueOf(it) }.getOrNull() },
    bpm = bpm,
    composer = composer,
    artist = artist,
    artworkUrl = artworkUrl,
    hasLyrics = hasLyrics,
    hasChords = hasChords,
    hasAudio = hasAudio,
    songbookName = songbookName,
    songbookNumber = songbookNumber,
    createdAt = Instant.fromEpochMilliseconds(createdAtEpochMillis)
)

fun SongLyricSection.toEntity() = SongLyricSectionEntity(
    id = id,
    songId = songId,
    language = language.name,
    sectionLabel = sectionLabel,
    lyrics = lyrics,
    translitLyrics = translitLyrics,
    translationTa = translationTa,
    orderIndex = orderIndex
)

fun SongLyricSectionEntity.toDomain() = SongLyricSection(
    id = id,
    songId = songId,
    language = runCatching { Language.valueOf(language) }.getOrDefault(Language.ENGLISH),
    sectionLabel = sectionLabel,
    lyrics = lyrics,
    translitLyrics = translitLyrics,
    translationTa = translationTa,
    orderIndex = orderIndex
)

fun SongMedia.toEntity() = SongMediaEntity(
    songId = songId,
    audioUrl = audioUrl,
    youtubeUrl = youtubeUrl,
    pdfUrl = pdfUrl,
    presentationUrl = presentationUrl,
    practiceUrl = practiceUrl
)

fun SongMediaEntity.toDomain() = SongMedia(
    songId = songId,
    audioUrl = audioUrl,
    youtubeUrl = youtubeUrl,
    pdfUrl = pdfUrl,
    presentationUrl = presentationUrl,
    practiceUrl = practiceUrl
)
