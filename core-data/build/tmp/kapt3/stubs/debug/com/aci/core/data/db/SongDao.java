package com.aci.core.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\bH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u000e\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\bH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\'J\u0018\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\'J\u0018\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u00122\u0006\u0010\u0017\u001a\u00020\u0014H\'J\u0014\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\b0\u0012H\'\u00a8\u0006\u0019"}, d2 = {"Lcom/aci/core/data/db/SongDao;", "", "countSongs", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertLyrics", "", "lyrics", "", "Lcom/aci/core/data/db/SongLyricSectionEntity;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertMedia", "media", "Lcom/aci/core/data/db/SongMediaEntity;", "insertSongs", "songs", "Lcom/aci/core/data/db/SongEntity;", "observeLyrics", "Lkotlinx/coroutines/flow/Flow;", "songId", "", "observeMedia", "observeSong", "id", "observeSongs", "core-data_debug"})
@androidx.room.Dao()
public abstract interface SongDao {
    
    @androidx.room.Query(value = "SELECT * FROM songs ORDER BY titleEn")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.aci.core.data.db.SongEntity>> observeSongs();
    
    @androidx.room.Query(value = "SELECT * FROM songs WHERE id = :id")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.aci.core.data.db.SongEntity> observeSong(@org.jetbrains.annotations.NotNull()
    java.lang.String id);
    
    @androidx.room.Query(value = "SELECT * FROM song_lyric_sections WHERE songId = :songId ORDER BY orderIndex")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.aci.core.data.db.SongLyricSectionEntity>> observeLyrics(@org.jetbrains.annotations.NotNull()
    java.lang.String songId);
    
    @androidx.room.Query(value = "SELECT * FROM song_media WHERE songId = :songId LIMIT 1")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.aci.core.data.db.SongMediaEntity> observeMedia(@org.jetbrains.annotations.NotNull()
    java.lang.String songId);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM songs")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object countSongs(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertSongs(@org.jetbrains.annotations.NotNull()
    java.util.List<com.aci.core.data.db.SongEntity> songs, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertLyrics(@org.jetbrains.annotations.NotNull()
    java.util.List<com.aci.core.data.db.SongLyricSectionEntity> lyrics, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertMedia(@org.jetbrains.annotations.NotNull()
    java.util.List<com.aci.core.data.db.SongMediaEntity> media, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}