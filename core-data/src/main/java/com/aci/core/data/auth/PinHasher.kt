package com.aci.core.data.auth

import android.util.Base64
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

/**
 * Salted PBKDF2 hashing for sign-in PINs, so the database never holds the PIN itself.
 *
 * A four-digit PIN only has 10,000 possible values, so this does not make it strong — someone
 * with the database file and patience can still work through the space. What it does buy is
 * that a casual look at the table (or a leaked backup) doesn't hand over a PIN people may well
 * have reused elsewhere, and the iteration count makes bulk cracking slow rather than instant.
 * Real strength would need a longer secret or a server-side auth provider.
 *
 * Uses HMAC-SHA1 rather than SHA-256 because `PBKDF2WithHmacSHA256` only exists from API 26 and
 * this module supports API 24. PBKDF2-HMAC-SHA1 remains sound for key derivation.
 */
object PinHasher {
    private const val ALGORITHM = "PBKDF2WithHmacSHA1"
    private const val ITERATIONS = 150_000
    private const val KEY_LENGTH_BITS = 256
    private const val SALT_BYTES = 16
    private const val PREFIX = "pbkdf2"

    /** Returns `pbkdf2$<iterations>$<salt>$<hash>` — self-describing so it can be upgraded later. */
    fun hash(pin: String): String {
        val salt = ByteArray(SALT_BYTES).also { SecureRandom().nextBytes(it) }
        val hash = derive(pin, salt, ITERATIONS)
        return listOf(PREFIX, ITERATIONS.toString(), salt.encode(), hash.encode()).joinToString("$")
    }

    /** Constant-time comparison against a stored [hash] produced by [hash]. */
    fun verify(pin: String, stored: String): Boolean {
        val parts = stored.split("$")
        if (parts.size != 4 || parts[0] != PREFIX) return false
        val iterations = parts[1].toIntOrNull() ?: return false
        val salt = runCatching { parts[2].decode() }.getOrNull() ?: return false
        val expected = runCatching { parts[3].decode() }.getOrNull() ?: return false
        return derive(pin, salt, iterations).constantTimeEquals(expected)
    }

    private fun derive(pin: String, salt: ByteArray, iterations: Int): ByteArray =
        SecretKeyFactory.getInstance(ALGORITHM)
            .generateSecret(PBEKeySpec(pin.toCharArray(), salt, iterations, KEY_LENGTH_BITS))
            .encoded

    private fun ByteArray.encode(): String = Base64.encodeToString(this, Base64.NO_WRAP)

    private fun String.decode(): ByteArray = Base64.decode(this, Base64.NO_WRAP)

    private fun ByteArray.constantTimeEquals(other: ByteArray): Boolean {
        if (size != other.size) return false
        var diff = 0
        for (i in indices) diff = diff or (this[i].toInt() xor other[i].toInt())
        return diff == 0
    }
}
