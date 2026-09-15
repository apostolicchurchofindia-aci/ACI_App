package com.aci.core.domain.repository

import com.aci.core.domain.model.Event
import com.aci.core.domain.model.EventRegistration
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun observeEvents(): Flow<List<Event>>
    fun observeEvent(id: String): Flow<Event?>
    fun observeMyRegistrations(userId: String): Flow<List<EventRegistration>>

    suspend fun isEmpty(): Boolean
    suspend fun upsertEvents(events: List<Event>)
    suspend fun register(registration: EventRegistration)
    suspend fun checkIn(registrationId: String)
}
