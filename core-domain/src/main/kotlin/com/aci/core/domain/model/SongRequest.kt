package com.aci.core.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
enum class SongRequestStatus { PENDING, APPROVED, REJECTED }

/**
 * A song a member has asked to be added to the library. Starts [SongRequestStatus.PENDING]
 * and only becomes a real [Song] in the catalog once an Admin/Super Admin approves it
 * (see [com.aci.core.domain.repository.SongRequestRepository.approve]) — members can't add
 * songs directly.
 */
@Serializable
data class SongRequest(
    val id: String,
    val titleEn: String,
    val titleNative: String = "",
    val language: String = "",
    val notes: String = "",
    val requestedByName: String = "",
    val status: SongRequestStatus = SongRequestStatus.PENDING,
    val createdAt: Instant = Instant.DISTANT_PAST,
    val reviewedAt: Instant? = null,
    val reviewNote: String = ""
)
