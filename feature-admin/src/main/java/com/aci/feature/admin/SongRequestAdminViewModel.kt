package com.aci.feature.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aci.core.data.di.ServiceLocator
import com.aci.core.domain.model.SongRequest
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Approve/reject queue for member-submitted [SongRequest]s. Gated by role via
 * [com.aci.feature.auth.AuthViewModel]'s session at the call site (see `MainActivity`) —
 * only signed-in Branch/ACI/Super Admin accounts reach this screen.
 */
class SongRequestAdminViewModel : ViewModel() {
    private val repository = ServiceLocator.songRequestRepository

    val pending: StateFlow<List<SongRequest>> = repository.observePending()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun approve(request: SongRequest) {
        viewModelScope.launch { repository.approve(request.id) }
    }

    fun reject(request: SongRequest) {
        viewModelScope.launch { repository.reject(request.id) }
    }
}
