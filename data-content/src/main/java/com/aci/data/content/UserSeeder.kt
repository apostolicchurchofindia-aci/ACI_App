package com.aci.data.content

import android.content.Context
import com.aci.core.data.repository.RoomUserRepository
import kotlinx.serialization.Serializable

/**
 * Seeds the local sign-in table from `users.json` on first launch, so pre-seeded accounts and
 * anyone who registers later live in the same Room table.
 *
 * The demo PINs are read here rather than from [ChurchContent.users] because credentials are
 * kept off the shared `User` model — they're hashed on the way into the database and the raw
 * values never leave this call.
 */
object UserSeeder {

    @Serializable
    private data class SeedCredential(val id: String, val pin: String = "")

    suspend fun seedIfNeeded(repository: RoomUserRepository, context: Context) {
        val credentials: List<SeedCredential> = JsonAssetLoader.load(context, "data/users.json")
        val pinsByUserId = credentials.filter { it.pin.isNotBlank() }.associate { it.id to it.pin }
        repository.seedIfNeeded(ChurchContent.users, pinsByUserId)
    }
}
