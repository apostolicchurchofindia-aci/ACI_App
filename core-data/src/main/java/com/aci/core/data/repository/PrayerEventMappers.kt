package com.aci.core.data.repository

import com.aci.core.data.db.EventEntity
import com.aci.core.data.db.EventRegistrationEntity
import com.aci.core.data.db.PrayerRequestEntity
import com.aci.core.data.db.TestimonyEntity
import com.aci.core.domain.enum.ContentStatus
import com.aci.core.domain.enum.EventCategory
import com.aci.core.domain.model.Event
import com.aci.core.domain.model.EventRegistration
import com.aci.core.domain.model.PrayerRequest
import com.aci.core.domain.model.Testimony
import kotlinx.datetime.Instant

fun PrayerRequest.toEntity() = PrayerRequestEntity(
    id = id, userId = userId, branchId = branchId, text = text,
    isAnonymous = isAnonymous, isPrivate = isPrivate, prayedCount = prayedCount,
    answered = answered, answeredAtEpochMillis = answeredAt?.toEpochMilliseconds(),
    createdAtEpochMillis = createdAt.toEpochMilliseconds(), status = status.name
)

fun PrayerRequestEntity.toDomain() = PrayerRequest(
    id = id, userId = userId, branchId = branchId, text = text,
    isAnonymous = isAnonymous, isPrivate = isPrivate, prayedCount = prayedCount,
    answered = answered, answeredAt = answeredAtEpochMillis?.let { Instant.fromEpochMilliseconds(it) },
    createdAt = Instant.fromEpochMilliseconds(createdAtEpochMillis),
    status = runCatching { ContentStatus.valueOf(status) }.getOrDefault(ContentStatus.PUBLISHED)
)

fun Testimony.toEntity() = TestimonyEntity(
    id = id, userId = userId, branchId = branchId, title = title, description = description,
    mediaUrlsJson = mediaUrlsJson, isPublic = isPublic, approved = approved,
    createdAtEpochMillis = createdAt.toEpochMilliseconds()
)

fun TestimonyEntity.toDomain() = Testimony(
    id = id, userId = userId, branchId = branchId, title = title, description = description,
    mediaUrlsJson = mediaUrlsJson, isPublic = isPublic, approved = approved,
    createdAt = Instant.fromEpochMilliseconds(createdAtEpochMillis)
)

fun Event.toEntity() = EventEntity(
    id = id, title = title, category = category.name, description = description, imageUrl = imageUrl,
    startAtEpochMillis = startAt?.toEpochMilliseconds(), endAtEpochMillis = endAt?.toEpochMilliseconds(),
    locationName = locationName, address = address, branchId = branchId,
    registrationRequired = registrationRequired, maxSeats = maxSeats, qrRequired = qrRequired
)

fun EventEntity.toDomain() = Event(
    id = id, title = title,
    category = runCatching { EventCategory.valueOf(category) }.getOrDefault(EventCategory.OTHER),
    description = description, imageUrl = imageUrl,
    startAt = startAtEpochMillis?.let { Instant.fromEpochMilliseconds(it) },
    endAt = endAtEpochMillis?.let { Instant.fromEpochMilliseconds(it) },
    locationName = locationName, address = address, branchId = branchId,
    registrationRequired = registrationRequired, maxSeats = maxSeats, qrRequired = qrRequired
)

fun EventRegistration.toEntity() = EventRegistrationEntity(
    id = id, eventId = eventId, userId = userId, name = name, phone = phone, seats = seats,
    registeredAtEpochMillis = registeredAt.toEpochMilliseconds(),
    checkedInAtEpochMillis = checkedInAt?.toEpochMilliseconds(), qrcodePayload = qrcodePayload
)

fun EventRegistrationEntity.toDomain() = EventRegistration(
    id = id, eventId = eventId, userId = userId, name = name, phone = phone, seats = seats,
    registeredAt = Instant.fromEpochMilliseconds(registeredAtEpochMillis),
    checkedInAt = checkedInAtEpochMillis?.let { Instant.fromEpochMilliseconds(it) },
    qrcodePayload = qrcodePayload
)
