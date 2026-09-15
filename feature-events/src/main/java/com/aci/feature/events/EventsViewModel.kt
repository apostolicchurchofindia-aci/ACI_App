package com.aci.feature.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aci.core.data.di.ServiceLocator
import com.aci.core.domain.model.Event
import com.aci.core.domain.model.EventRegistration
import com.aci.data.content.CurrentUser
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class EventsViewModel : ViewModel() {
    private val repository = ServiceLocator.eventRepository

    val events: StateFlow<List<Event>> = repository.observeEvents()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun register(event: Event, name: String, phone: String, seats: Int, onDone: (String) -> Unit) {
        val id = "reg-${Clock.System.now().toEpochMilliseconds()}"
        viewModelScope.launch {
            repository.register(
                EventRegistration(
                    id = id,
                    eventId = event.id,
                    userId = CurrentUser.ID,
                    name = name,
                    phone = phone,
                    seats = seats,
                    registeredAt = Clock.System.now(),
                    qrcodePayload = "${event.id}:$id"
                )
            )
            onDone(id)
        }
    }
}
