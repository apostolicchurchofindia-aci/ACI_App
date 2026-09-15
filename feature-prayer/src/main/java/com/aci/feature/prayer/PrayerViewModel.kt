package com.aci.feature.prayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aci.core.data.di.ServiceLocator
import com.aci.core.domain.enum.ContentStatus
import com.aci.core.domain.model.PrayerRequest
import com.aci.data.content.BranchIds
import com.aci.data.content.CurrentUser
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class PrayerViewModel : ViewModel() {
    private val repository = ServiceLocator.prayerRepository

    val activeRequests: StateFlow<List<PrayerRequest>> = repository.observeActiveRequests()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val answeredRequests: StateFlow<List<PrayerRequest>> = repository.observeAnsweredRequests()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val testimonies = repository.observePublicTestimonies()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun prayFor(request: PrayerRequest) {
        viewModelScope.launch { repository.prayFor(request.id) }
    }

    fun markAnswered(request: PrayerRequest) {
        viewModelScope.launch { repository.markAnswered(request.id) }
    }

    fun submit(text: String, isAnonymous: Boolean, isPrivate: Boolean) {
        viewModelScope.launch {
            repository.submitRequest(
                PrayerRequest(
                    id = "prayer-${Clock.System.now().toEpochMilliseconds()}",
                    userId = CurrentUser.ID,
                    branchId = BranchIds.BHARATH_NAGAR,
                    text = text,
                    isAnonymous = isAnonymous,
                    isPrivate = isPrivate,
                    createdAt = Clock.System.now(),
                    status = ContentStatus.PUBLISHED
                )
            )
        }
    }
}
