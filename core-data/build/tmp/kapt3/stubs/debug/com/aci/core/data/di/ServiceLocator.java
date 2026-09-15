package com.aci.core.data.di;

/**
 * Manual dependency container (no DI framework yet — matches the module-boundary plan
 * documented for a future Hilt upgrade). Call [init] once from Application.onCreate.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u000e@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u0012@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0003\u001a\u00020\u0016@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001e\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0003\u001a\u00020\u001a@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001e\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0003\u001a\u00020\u001e@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!\u00a8\u0006&"}, d2 = {"Lcom/aci/core/data/di/ServiceLocator;", "", "()V", "<set-?>", "Lcom/aci/core/domain/repository/BibleRepository;", "bibleRepository", "getBibleRepository", "()Lcom/aci/core/domain/repository/BibleRepository;", "database", "Lcom/aci/core/data/db/AciDatabase;", "Lcom/aci/core/domain/repository/EventRepository;", "eventRepository", "getEventRepository", "()Lcom/aci/core/domain/repository/EventRepository;", "Lcom/aci/core/domain/repository/PrayerRepository;", "prayerRepository", "getPrayerRepository", "()Lcom/aci/core/domain/repository/PrayerRepository;", "Lcom/aci/core/domain/repository/ReadingPlanRepository;", "readingPlanRepository", "getReadingPlanRepository", "()Lcom/aci/core/domain/repository/ReadingPlanRepository;", "Lcom/aci/core/domain/repository/SongRepository;", "songRepository", "getSongRepository", "()Lcom/aci/core/domain/repository/SongRepository;", "Lcom/aci/core/domain/repository/SongRequestRepository;", "songRequestRepository", "getSongRequestRepository", "()Lcom/aci/core/domain/repository/SongRequestRepository;", "Lcom/aci/core/data/repository/RoomUserRepository;", "userRepository", "getUserRepository", "()Lcom/aci/core/data/repository/RoomUserRepository;", "init", "", "context", "Landroid/content/Context;", "core-data_debug"})
public final class ServiceLocator {
    @org.jetbrains.annotations.Nullable()
    private static com.aci.core.data.db.AciDatabase database;
    private static com.aci.core.domain.repository.SongRepository songRepository;
    private static com.aci.core.domain.repository.BibleRepository bibleRepository;
    private static com.aci.core.domain.repository.PrayerRepository prayerRepository;
    private static com.aci.core.domain.repository.EventRepository eventRepository;
    private static com.aci.core.domain.repository.SongRequestRepository songRequestRepository;
    private static com.aci.core.data.repository.RoomUserRepository userRepository;
    private static com.aci.core.domain.repository.ReadingPlanRepository readingPlanRepository;
    @org.jetbrains.annotations.NotNull()
    public static final com.aci.core.data.di.ServiceLocator INSTANCE = null;
    
    private ServiceLocator() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aci.core.domain.repository.SongRepository getSongRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aci.core.domain.repository.BibleRepository getBibleRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aci.core.domain.repository.PrayerRepository getPrayerRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aci.core.domain.repository.EventRepository getEventRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aci.core.domain.repository.SongRequestRepository getSongRequestRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aci.core.data.repository.RoomUserRepository getUserRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aci.core.domain.repository.ReadingPlanRepository getReadingPlanRepository() {
        return null;
    }
    
    public final void init(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
}