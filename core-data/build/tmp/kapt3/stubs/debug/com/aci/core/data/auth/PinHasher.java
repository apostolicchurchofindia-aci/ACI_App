package com.aci.core.data.auth;

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
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0006H\u0002J\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004J\u0014\u0010\u0013\u001a\u00020\u0011*\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000bH\u0002J\f\u0010\u0015\u001a\u00020\u000b*\u00020\u0004H\u0002J\f\u0010\u0016\u001a\u00020\u0004*\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/aci/core/data/auth/PinHasher;", "", "()V", "ALGORITHM", "", "ITERATIONS", "", "KEY_LENGTH_BITS", "PREFIX", "SALT_BYTES", "derive", "", "pin", "salt", "iterations", "hash", "verify", "", "stored", "constantTimeEquals", "other", "decode", "encode", "core-data_debug"})
public final class PinHasher {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int ITERATIONS = 150000;
    private static final int KEY_LENGTH_BITS = 256;
    private static final int SALT_BYTES = 16;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFIX = "pbkdf2";
    @org.jetbrains.annotations.NotNull()
    public static final com.aci.core.data.auth.PinHasher INSTANCE = null;
    
    private PinHasher() {
        super();
    }
    
    /**
     * Returns `pbkdf2$<iterations>$<salt>$<hash>` — self-describing so it can be upgraded later.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String hash(@org.jetbrains.annotations.NotNull()
    java.lang.String pin) {
        return null;
    }
    
    /**
     * Constant-time comparison against a stored [hash] produced by [hash].
     */
    public final boolean verify(@org.jetbrains.annotations.NotNull()
    java.lang.String pin, @org.jetbrains.annotations.NotNull()
    java.lang.String stored) {
        return false;
    }
    
    private final byte[] derive(java.lang.String pin, byte[] salt, int iterations) {
        return null;
    }
    
    private final java.lang.String encode(byte[] $this$encode) {
        return null;
    }
    
    private final byte[] decode(java.lang.String $this$decode) {
        return null;
    }
    
    private final boolean constantTimeEquals(byte[] $this$constantTimeEquals, byte[] other) {
        return false;
    }
}