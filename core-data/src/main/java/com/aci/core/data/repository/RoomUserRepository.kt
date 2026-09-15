package com.aci.core.data.repository

import com.aci.core.data.auth.PinHasher
import com.aci.core.data.auth.SessionStore
import com.aci.core.data.db.UserDao
import com.aci.core.domain.enum.Role
import com.aci.core.domain.model.User
import com.aci.core.domain.repository.AuthResult
import com.aci.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.datetime.Clock

class RoomUserRepository(
    private val dao: UserDao,
    private val sessionStore: SessionStore
) : UserRepository {
    private val _currentUser = MutableStateFlow<User?>(null)

    override fun observeCurrentUser(): StateFlow<User?> = _currentUser.asStateFlow()

    override suspend fun register(fullName: String, email: String, phone: String, pin: String): AuthResult {
        val normalizedEmail = email.normalizeEmail()
        if (dao.findByEmail(normalizedEmail) != null) return AuthResult.EmailAlreadyRegistered

        val user = User(
            id = "user-${Clock.System.now().toEpochMilliseconds()}",
            email = normalizedEmail,
            phone = phone.trim(),
            role = Role.MEMBER,
            fullName = fullName.trim(),
            createdAt = Clock.System.now()
        )
        dao.insert(user.toEntity(PinHasher.hash(pin.trim())))
        signIn(user)
        return AuthResult.Success
    }

    override suspend fun login(email: String, pin: String): AuthResult {
        val entity = dao.findByEmail(email.normalizeEmail()) ?: return AuthResult.InvalidCredentials
        if (entity.pinHash.isBlank()) return AuthResult.InvalidCredentials
        if (!PinHasher.verify(pin.trim(), entity.pinHash)) return AuthResult.InvalidCredentials
        signIn(entity.toDomain())
        return AuthResult.Success
    }

    override suspend fun logout() {
        _currentUser.value = null
        sessionStore.clear()
    }

    /** Restores the previous sign-in on launch; call once after seeding. */
    suspend fun restoreSession() {
        val savedId = sessionStore.savedUserId() ?: return
        _currentUser.value = dao.findById(savedId)?.toDomain()
            ?: run { sessionStore.clear(); null }
    }

    suspend fun seedIfNeeded(users: List<User>, rawPinsByUserId: Map<String, String>) {
        if (dao.count() > 0) return
        dao.insertAll(
            users.map { user ->
                val rawPin = rawPinsByUserId[user.id].orEmpty()
                // Accounts with no seeded PIN get a blank hash, which login always rejects.
                user.toEntity(if (rawPin.isBlank()) "" else PinHasher.hash(rawPin))
            }
        )
    }

    private suspend fun signIn(user: User) {
        _currentUser.value = user
        sessionStore.save(user.id)
    }

    // Registration lowercases, so sign-in has to as well or a capitalised address never matches.
    private fun String.normalizeEmail(): String = trim().lowercase()
}
