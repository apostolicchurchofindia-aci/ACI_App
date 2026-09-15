package com.aci.core.data.repository

import com.aci.core.data.db.SongRequestEntity
import com.aci.core.domain.model.SongRequest
import com.aci.core.domain.model.SongRequestStatus
import kotlinx.datetime.Instant

fun SongRequest.toEntity() = SongRequestEntity(
    id = id,
    titleEn = titleEn,
    titleNative = titleNative,
    language = language,
    notes = notes,
    requestedByName = requestedByName,
    status = status.name,
    createdAtEpochMillis = createdAt.toEpochMilliseconds(),
    reviewedAtEpochMillis = reviewedAt?.toEpochMilliseconds(),
    reviewNote = reviewNote
)

fun SongRequestEntity.toDomain() = SongRequest(
    id = id,
    titleEn = titleEn,
    titleNative = titleNative,
    language = language,
    notes = notes,
    requestedByName = requestedByName,
    status = runCatching { SongRequestStatus.valueOf(status) }.getOrDefault(SongRequestStatus.PENDING),
    createdAt = Instant.fromEpochMilliseconds(createdAtEpochMillis),
    reviewedAt = reviewedAtEpochMillis?.let { Instant.fromEpochMilliseconds(it) },
    reviewNote = reviewNote
)
