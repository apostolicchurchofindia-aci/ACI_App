package com.aci.feature.bible

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aci.core.data.di.ServiceLocator
import com.aci.core.domain.model.ReadingPlanState
import com.aci.data.content.ChurchContent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@OptIn(ExperimentalCoroutinesApi::class)
class ReadingPlanViewModel : ViewModel() {
    private val repository = ServiceLocator.readingPlanRepository
    private val users = ServiceLocator.userRepository

    val plan = ChurchContent.readingPlan

    /** Progress belongs to the signed-in member, so each account tracks its own year. */
    val state: StateFlow<ReadingPlanState?> = users.observeCurrentUser()
        .flatMapLatest { user ->
            if (user == null) flowOf(null) else repository.observeState(user.id)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    fun today(): LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())

    fun start() {
        viewModelScope.launch {
            val user = users.observeCurrentUser().first() ?: return@launch
            repository.start(user.id, today())
        }
    }
}
