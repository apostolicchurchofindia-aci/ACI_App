package com.aci.core.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010\f\u001a\u00020\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0013H\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u0018\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u00162\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0014\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u0016H\'J\u001c\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u000e0\u00162\u0006\u0010\u0019\u001a\u00020\u0005H\'J\u001c\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u000e0\u00162\u0006\u0010\u001b\u001a\u00020\u0005H\'\u00a8\u0006\u001c"}, d2 = {"Lcom/aci/core/data/db/EventDao;", "", "checkIn", "", "id", "", "checkedInAt", "", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "countEvents", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertEvents", "events", "", "Lcom/aci/core/data/db/EventEntity;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertRegistration", "registration", "Lcom/aci/core/data/db/EventRegistrationEntity;", "(Lcom/aci/core/data/db/EventRegistrationEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeEvent", "Lkotlinx/coroutines/flow/Flow;", "observeEvents", "observeMyRegistrations", "userId", "observeRegistrationsForEvent", "eventId", "core-data_debug"})
@androidx.room.Dao()
public abstract interface EventDao {
    
    @androidx.room.Query(value = "SELECT * FROM events ORDER BY startAtEpochMillis ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.aci.core.data.db.EventEntity>> observeEvents();
    
    @androidx.room.Query(value = "SELECT * FROM events WHERE id = :id LIMIT 1")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.aci.core.data.db.EventEntity> observeEvent(@org.jetbrains.annotations.NotNull()
    java.lang.String id);
    
    @androidx.room.Query(value = "SELECT * FROM event_registrations WHERE eventId = :eventId")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.aci.core.data.db.EventRegistrationEntity>> observeRegistrationsForEvent(@org.jetbrains.annotations.NotNull()
    java.lang.String eventId);
    
    @androidx.room.Query(value = "SELECT * FROM event_registrations WHERE userId = :userId ORDER BY registeredAtEpochMillis DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.aci.core.data.db.EventRegistrationEntity>> observeMyRegistrations(@org.jetbrains.annotations.NotNull()
    java.lang.String userId);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM events")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object countEvents(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertEvents(@org.jetbrains.annotations.NotNull()
    java.util.List<com.aci.core.data.db.EventEntity> events, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertRegistration(@org.jetbrains.annotations.NotNull()
    com.aci.core.data.db.EventRegistrationEntity registration, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE event_registrations SET checkedInAtEpochMillis = :checkedInAt WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object checkIn(@org.jetbrains.annotations.NotNull()
    java.lang.String id, long checkedInAt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}