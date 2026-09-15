package com.aci.core.data.repository

import com.aci.core.data.db.EventDao
import com.aci.core.domain.model.Event
import com.aci.core.domain.model.EventRegistration
import com.aci.core.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class RoomEventRepository(private val dao: EventDao) : EventRepository {

    override fun observeEvents(): Flow<List<Event>> =
        dao.observeEvents().map { it.map { e -> e.toDomain() } }

    override fun observeEvent(id: String): Flow<Event?> =
        dao.observeEvent(id).map { it?.toDomain() }

    override fun observeMyRegistrations(userId: String): Flow<List<EventRegistration>> =
        dao.observeMyRegistrations(userId).map { it.map { e -> e.toDomain() } }

    override suspend fun isEmpty(): Boolean = dao.countEvents() == 0

    override suspend fun upsertEvents(events: List<Event>) {
        dao.insertEvents(events.map { it.toEntity() })
    }

    override suspend fun register(registration: EventRegistration) {
        dao.insertRegistration(registration.toEntity())
    }

    override suspend fun checkIn(registrationId: String) {
        dao.checkIn(registrationId, Clock.System.now().toEpochMilliseconds())
    }
}
