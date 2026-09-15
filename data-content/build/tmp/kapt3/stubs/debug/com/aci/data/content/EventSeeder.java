package com.aci.data.content;

/**
 * Seeds real ACI events (see `events.json` provenance note) into Room on first launch.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\t\u00a8\u0006\n"}, d2 = {"Lcom/aci/data/content/EventSeeder;", "", "()V", "seedIfNeeded", "", "repository", "Lcom/aci/core/domain/repository/EventRepository;", "context", "Landroid/content/Context;", "(Lcom/aci/core/domain/repository/EventRepository;Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data-content_debug"})
public final class EventSeeder {
    @org.jetbrains.annotations.NotNull()
    public static final com.aci.data.content.EventSeeder INSTANCE = null;
    
    private EventSeeder() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object seedIfNeeded(@org.jetbrains.annotations.NotNull()
    com.aci.core.domain.repository.EventRepository repository, @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}