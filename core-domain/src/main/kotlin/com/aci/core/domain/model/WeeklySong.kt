package com.aci.core.domain.model

import com.aci.core.domain.enum.ContentStatus
import com.aci.core.domain.enum.SongKey
import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate

@Serializable
data class WeeklySong(
    val id: String,
    val sundayDate: LocalDate,
    val branchId: String,
    val songId: String,
    val key: SongKey? = null,
    val bpm: Int? = null,
    val worshipLeaderName: String = "",
    val practiceAudioUrl: String = "",
    val youtubeVideoId: String = "",
    val notes: String = "",
    val status: ContentStatus = ContentStatus.DRAFT
)
