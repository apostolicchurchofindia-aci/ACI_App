package com.aci.core.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u0006H\u0096@\u00a2\u0006\u0002\u0010\u0007J\u001c\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\t2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\t2\u0006\u0010\u0012\u001a\u00020\rH\u0016J\u0014\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\n0\tH\u0016J8\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\n2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000f0\nH\u0096@\u00a2\u0006\u0002\u0010\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/aci/core/data/repository/RoomSongRepository;", "Lcom/aci/core/domain/repository/SongRepository;", "dao", "Lcom/aci/core/data/db/SongDao;", "(Lcom/aci/core/data/db/SongDao;)V", "isEmpty", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeLyrics", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/aci/core/domain/model/SongLyricSection;", "songId", "", "observeMedia", "Lcom/aci/core/domain/model/SongMedia;", "observeSong", "Lcom/aci/core/domain/model/Song;", "id", "observeSongs", "upsertAll", "", "songs", "lyrics", "media", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "core-data_debug"})
public final class RoomSongRepository implements com.aci.core.domain.repository.SongRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.aci.core.data.db.SongDao dao = null;
    
    public RoomSongRepository(@org.jetbrains.annotations.NotNull()
    com.aci.core.data.db.SongDao dao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.aci.core.domain.model.Song>> observeSongs() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.aci.core.domain.model.Song> observeSong(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.aci.core.domain.model.SongLyricSection>> observeLyrics(@org.jetbrains.annotations.NotNull()
    java.lang.String songId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.aci.core.domain.model.SongMedia> observeMedia(@org.jetbrains.annotations.NotNull()
    java.lang.String songId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object isEmpty(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object upsertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.aci.core.domain.model.Song> songs, @org.jetbrains.annotations.NotNull()
    java.util.List<com.aci.core.domain.model.SongLyricSection> lyrics, @org.jetbrains.annotations.NotNull()
    java.util.List<com.aci.core.domain.model.SongMedia> media, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}