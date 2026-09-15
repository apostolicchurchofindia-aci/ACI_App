package com.aci.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aci.core.data.di.ServiceLocator
import com.aci.core.domain.model.User
import com.aci.core.domain.repository.AuthResult
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val repository = ServiceLocator.userRepository

    val currentUser: StateFlow<User?> = repository.observeCurrentUser()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    fun register(
        fullName: String,
        email: String,
        phone: String,
        pin: String,
        onResult: (AuthResult) -> Unit
    ) {
        viewModelScope.launch {
            onResult(repository.register(fullName, email, phone, pin))
        }
    }

    fun login(email: String, pin: String, onResult: (AuthResult) -> Unit) {
        viewModelScope.launch {
            onResult(repository.login(email, pin))
        }
    }

    fun logout() {
        viewModelScope.launch { repository.logout() }
    }
}
