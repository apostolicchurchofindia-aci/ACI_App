package com.aci.data.content;

/**
 * Seeds the app's local sign-in table from `users.json` (including the demo admin PINs) on
 * first launch, so both the pre-seeded accounts and anyone who registers afterward live in
 * the same Room table.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/aci/data/content/UserSeeder;", "", "()V", "seedIfNeeded", "", "repository", "Lcom/aci/core/data/repository/RoomUserRepository;", "(Lcom/aci/core/data/repository/RoomUserRepository;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data-content_debug"})
public final class UserSeeder {
    @org.jetbrains.annotations.NotNull()
    public static final com.aci.data.content.UserSeeder INSTANCE = null;
    
    private UserSeeder() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object seedIfNeeded(@org.jetbrains.annotations.NotNull()
    com.aci.core.data.repository.RoomUserRepository repository, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}