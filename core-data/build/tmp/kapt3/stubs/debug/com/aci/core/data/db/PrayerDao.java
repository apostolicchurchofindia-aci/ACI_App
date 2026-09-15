package com.aci.core.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u001e\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u0014\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00150\u0014H\'J\u0014\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00150\u0014H\'J\u001c\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00150\u00142\u0006\u0010\u0018\u001a\u00020\u0005H\'J\u0014\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00150\u0014H\'J\u0014\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00150\u0014H\'\u00a8\u0006\u001b"}, d2 = {"Lcom/aci/core/data/db/PrayerDao;", "", "incrementPrayedCount", "", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertPrayerRequest", "request", "Lcom/aci/core/data/db/PrayerRequestEntity;", "(Lcom/aci/core/data/db/PrayerRequestEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertTestimony", "testimony", "Lcom/aci/core/data/db/TestimonyEntity;", "(Lcom/aci/core/data/db/TestimonyEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markAnswered", "answeredAt", "", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeActiveRequests", "Lkotlinx/coroutines/flow/Flow;", "", "observeAnsweredRequests", "observeMyRequests", "userId", "observePendingOrPrivateTestimonies", "observePublicTestimonies", "core-data_debug"})
@androidx.room.Dao()
public abstract interface PrayerDao {
    
    @androidx.room.Query(value = "SELECT * FROM prayer_requests WHERE isPrivate = 0 AND answered = 0 ORDER BY createdAtEpochMillis DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.aci.core.data.db.PrayerRequestEntity>> observeActiveRequests();
    
    @androidx.room.Query(value = "SELECT * FROM prayer_requests WHERE isPrivate = 0 AND answered = 1 ORDER BY answeredAtEpochMillis DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.aci.core.data.db.PrayerRequestEntity>> observeAnsweredRequests();
    
    @androidx.room.Query(value = "SELECT * FROM prayer_requests WHERE userId = :userId ORDER BY createdAtEpochMillis DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.aci.core.data.db.PrayerRequestEntity>> observeMyRequests(@org.jetbrains.annotations.NotNull()
    java.lang.String userId);
    
    @androidx.room.Query(value = "SELECT * FROM testimonies WHERE isPublic = 1 AND approved = 1 ORDER BY createdAtEpochMillis DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.aci.core.data.db.TestimonyEntity>> observePublicTestimonies();
    
    @androidx.room.Query(value = "SELECT * FROM testimonies WHERE isPublic = 0 OR approved = 0 ORDER BY createdAtEpochMillis DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.aci.core.data.db.TestimonyEntity>> observePendingOrPrivateTestimonies();
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertPrayerRequest(@org.jetbrains.annotations.NotNull()
    com.aci.core.data.db.PrayerRequestEntity request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE prayer_requests SET prayedCount = prayedCount + 1 WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object incrementPrayedCount(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE prayer_requests SET answered = 1, answeredAtEpochMillis = :answeredAt WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markAnswered(@org.jetbrains.annotations.NotNull()
    java.lang.String id, long answeredAt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertTestimony(@org.jetbrains.annotations.NotNull()
    com.aci.core.data.db.TestimonyEntity testimony, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}