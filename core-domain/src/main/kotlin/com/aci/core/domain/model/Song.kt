package com.aci.core.domain.model

import com.aci.core.domain.enum.SongKey
import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class Song(
    val id: String,
    val titleEn: String,
    /** Tamil-script rendering of the title: a real Tamil translation for the handful of songs
     *  that have one, otherwise the Telugu title mechanically transliterated into Tamil script
     *  (same approach as [com.aci.core.domain.model.SongLyricSection.translationTa]) so every
     *  song has something to show under a "Tamil" library view. */
    val titleTa: String = "",
    val titleTe: String? = null,
    /** Romanized transliteration of [titleTe], e.g. "Aayane Naa Sangeethamu". */
    val titleTeTranslit: String = "",
    /** Romanized transliteration of [titleTa]. */
    val titleTaTranslit: String = "",
    val language: String = "",
    val category: String = "",
    val key: SongKey? = null,
    val bpm: Int? = null,
    val composer: String = "",
    val artist: String = "",
    val artworkUrl: String = "",
    val hasLyrics: Boolean = false,
    val hasChords: Boolean = false,
    val hasAudio: Boolean = false,
    val songbookName: String = "",
    val songbookNumber: Int? = null,
    val createdAt: Instant = Instant.DISTANT_PAST
)
