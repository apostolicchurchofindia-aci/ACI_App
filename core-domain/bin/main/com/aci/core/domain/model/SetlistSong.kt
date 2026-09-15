package com.aci.core.domain.model

import com.aci.core.domain.enum.SongKey
import kotlinx.serialization.Serializable

@Serializable
data class SetlistSong(
    val setlistId: String,
    val songId: String,
    val orderIndex: Int = 0,
    val assignedKey: SongKey? = null,
    val bpm: Int? = null,
    val notes: String = "",
    val vocalistIdsJson: String = "",
    val musicianIdsJson: String = "",
    val transitionNote: String = ""
)
