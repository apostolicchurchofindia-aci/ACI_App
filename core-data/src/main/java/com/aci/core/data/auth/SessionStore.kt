package com.aci.core.data.auth

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.sessionDataStore: DataStore<Preferences> by preferencesDataStore(name = "aci_session")

/**
 * Remembers which member is signed in across app restarts. Only the user id is kept — the PIN
 * is never stored here, so restoring a session re-reads the account from Room rather than
 * re-authenticating.
 */
class SessionStore(private val context: Context) {
    private val userIdKey = stringPreferencesKey("current_user_id")

    suspend fun savedUserId(): String? = context.sessionDataStore.data.first()[userIdKey]

    suspend fun save(userId: String) {
        context.sessionDataStore.edit { it[userIdKey] = userId }
    }

    suspend fun clear() {
        context.sessionDataStore.edit { it.remove(userIdKey) }
    }
}
