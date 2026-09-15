package com.aci.core.domain.repository

import com.aci.core.domain.model.User
import kotlinx.coroutines.flow.Flow

sealed interface AuthResult {
    data object Success : AuthResult
    data object InvalidCredentials : AuthResult
    data object EmailAlreadyRegistered : AuthResult
}

interface UserRepository {
    /** The signed-in member, or null if signed out. Restored on launch from the saved session. */
    fun observeCurrentUser(): Flow<User?>

    suspend fun register(fullName: String, email: String, phone: String, pin: String): AuthResult
    suspend fun login(email: String, pin: String): AuthResult
    suspend fun logout()
}
