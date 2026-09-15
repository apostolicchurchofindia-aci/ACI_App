package com.aci.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SongFavorite(
    val userId: String,
    val songId: String
)
