package com.aci.core.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\nJ&\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0013\u001a\u00020\u00062\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u00a7@\u00a2\u0006\u0002\u0010\u0017J\u0016\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001aH\u00a7@\u00a2\u0006\u0002\u0010\u001bJ\u001c\u0010\u001c\u001a\u00020\u00062\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0015H\u00a7@\u00a2\u0006\u0002\u0010\u0017J\u001e\u0010\u001f\u001a\u00020 2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00150\"2\u0006\u0010\u0007\u001a\u00020\bH\'J\u001c\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00150\"2\u0006\u0010\u0007\u001a\u00020\bH\'J\u0014\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\"H\'J\u001c\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00150\"2\u0006\u0010\u0007\u001a\u00020\bH\'J$\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00150\"2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\'J\u0018\u0010\'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\"2\u0006\u0010\t\u001a\u00020\bH\'J,\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00150\"2\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u00032\u0006\u0010+\u001a\u00020\u0003H\'J$\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00150\"2\u0006\u0010)\u001a\u00020\b2\u0006\u0010-\u001a\u00020\u0003H\'J\u001c\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00150\"2\u0006\u0010/\u001a\u00020\bH\'\u00a8\u00060"}, d2 = {"Lcom/aci/core/data/db/BibleDao;", "", "countBooks", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteBookmark", "", "userId", "", "verseId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteNote", "createdAtEpochMillis", "", "(Ljava/lang/String;Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertBookmark", "bookmark", "Lcom/aci/core/data/db/VerseBookmarkEntity;", "(Lcom/aci/core/data/db/VerseBookmarkEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertBooks", "books", "", "Lcom/aci/core/data/db/BibleBookEntity;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertNote", "note", "Lcom/aci/core/data/db/VerseNoteEntity;", "(Lcom/aci/core/data/db/VerseNoteEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertVerses", "verses", "Lcom/aci/core/data/db/BibleVerseEntity;", "isBookmarked", "", "observeBookmarkedVerses", "Lkotlinx/coroutines/flow/Flow;", "observeBookmarks", "observeBooks", "observeNotes", "observeNotesForVerse", "observeVerse", "observeVerseRange", "bookId", "startChapter", "endChapter", "observeVerses", "chapter", "searchVerses", "query", "core-data_debug"})
@androidx.room.Dao()
public abstract interface BibleDao {
    
    @androidx.room.Query(value = "SELECT * FROM bible_books ORDER BY `order`")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.aci.core.data.db.BibleBookEntity>> observeBooks();
    
    @androidx.room.Query(value = "SELECT * FROM bible_verses WHERE bookId = :bookId AND chapter = :chapter ORDER BY verseNumber")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.aci.core.data.db.BibleVerseEntity>> observeVerses(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId, int chapter);
    
    @androidx.room.Query(value = "SELECT * FROM bible_verses WHERE bookId = :bookId AND chapter BETWEEN :startChapter AND :endChapter ORDER BY chapter, verseNumber")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.aci.core.data.db.BibleVerseEntity>> observeVerseRange(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId, int startChapter, int endChapter);
    
    @androidx.room.Query(value = "SELECT * FROM bible_verses WHERE id = :verseId LIMIT 1")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.aci.core.data.db.BibleVerseEntity> observeVerse(@org.jetbrains.annotations.NotNull()
    java.lang.String verseId);
    
    @androidx.room.Query(value = "SELECT * FROM bible_verses WHERE textEn LIKE \'%\' || :query || \'%\' LIMIT 100")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.aci.core.data.db.BibleVerseEntity>> searchVerses(@org.jetbrains.annotations.NotNull()
    java.lang.String query);
    
    @androidx.room.Query(value = "SELECT * FROM verse_bookmarks WHERE userId = :userId ORDER BY createdAtEpochMillis DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.aci.core.data.db.VerseBookmarkEntity>> observeBookmarks(@org.jetbrains.annotations.NotNull()
    java.lang.String userId);
    
    @androidx.room.Query(value = "SELECT v.* FROM bible_verses v\n           INNER JOIN verse_bookmarks b ON b.verseId = v.id\n           WHERE b.userId = :userId ORDER BY b.createdAtEpochMillis DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.aci.core.data.db.BibleVerseEntity>> observeBookmarkedVerses(@org.jetbrains.annotations.NotNull()
    java.lang.String userId);
    
    @androidx.room.Query(value = "SELECT * FROM verse_notes WHERE userId = :userId ORDER BY createdAtEpochMillis DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.aci.core.data.db.VerseNoteEntity>> observeNotes(@org.jetbrains.annotations.NotNull()
    java.lang.String userId);
    
    @androidx.room.Query(value = "SELECT * FROM verse_notes WHERE userId = :userId AND verseId = :verseId ORDER BY createdAtEpochMillis DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.aci.core.data.db.VerseNoteEntity>> observeNotesForVerse(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.lang.String verseId);
    
    @androidx.room.Query(value = "SELECT EXISTS(SELECT 1 FROM verse_bookmarks WHERE userId = :userId AND verseId = :verseId)")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isBookmarked(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.lang.String verseId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertBookmark(@org.jetbrains.annotations.NotNull()
    com.aci.core.data.db.VerseBookmarkEntity bookmark, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM verse_bookmarks WHERE userId = :userId AND verseId = :verseId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteBookmark(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.lang.String verseId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertNote(@org.jetbrains.annotations.NotNull()
    com.aci.core.data.db.VerseNoteEntity note, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM verse_notes WHERE userId = :userId AND verseId = :verseId AND createdAtEpochMillis = :createdAtEpochMillis")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteNote(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.lang.String verseId, long createdAtEpochMillis, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM bible_books")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object countBooks(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertBooks(@org.jetbrains.annotations.NotNull()
    java.util.List<com.aci.core.data.db.BibleBookEntity> books, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertVerses(@org.jetbrains.annotations.NotNull()
    java.util.List<com.aci.core.data.db.BibleVerseEntity> verses, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}