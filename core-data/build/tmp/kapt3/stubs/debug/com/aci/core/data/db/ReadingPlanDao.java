package com.aci.core.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\'J\u0018\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\b2\u0006\u0010\n\u001a\u00020\u000bH\'J\u0016\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/aci/core/data/db/ReadingPlanDao;", "", "insertChapterRead", "", "read", "Lcom/aci/core/data/db/ChapterReadEntity;", "(Lcom/aci/core/data/db/ChapterReadEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeChapterReads", "Lkotlinx/coroutines/flow/Flow;", "", "userId", "", "observeState", "Lcom/aci/core/data/db/ReadingPlanStateEntity;", "upsertState", "state", "(Lcom/aci/core/data/db/ReadingPlanStateEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "core-data_debug"})
@androidx.room.Dao()
public abstract interface ReadingPlanDao {
    
    @androidx.room.Query(value = "SELECT * FROM reading_plan_state WHERE userId = :userId LIMIT 1")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.aci.core.data.db.ReadingPlanStateEntity> observeState(@org.jetbrains.annotations.NotNull()
    java.lang.String userId);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertState(@org.jetbrains.annotations.NotNull()
    com.aci.core.data.db.ReadingPlanStateEntity state, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM chapter_reads WHERE userId = :userId")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.aci.core.data.db.ChapterReadEntity>> observeChapterReads(@org.jetbrains.annotations.NotNull()
    java.lang.String userId);
    
    @androidx.room.Insert(onConflict = 5)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertChapterRead(@org.jetbrains.annotations.NotNull()
    com.aci.core.data.db.ChapterReadEntity read, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}