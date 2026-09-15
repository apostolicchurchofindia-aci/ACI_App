package com.aci.core.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&\u00a8\u0006\u0012"}, d2 = {"Lcom/aci/core/data/db/AciDatabase;", "Landroidx/room/RoomDatabase;", "()V", "bibleDao", "Lcom/aci/core/data/db/BibleDao;", "eventDao", "Lcom/aci/core/data/db/EventDao;", "prayerDao", "Lcom/aci/core/data/db/PrayerDao;", "readingPlanDao", "Lcom/aci/core/data/db/ReadingPlanDao;", "songDao", "Lcom/aci/core/data/db/SongDao;", "songRequestDao", "Lcom/aci/core/data/db/SongRequestDao;", "userDao", "Lcom/aci/core/data/db/UserDao;", "Companion", "core-data_debug"})
@androidx.room.Database(entities = {com.aci.core.data.db.SongEntity.class, com.aci.core.data.db.SongLyricSectionEntity.class, com.aci.core.data.db.SongMediaEntity.class, com.aci.core.data.db.BibleBookEntity.class, com.aci.core.data.db.BibleVerseEntity.class, com.aci.core.data.db.VerseBookmarkEntity.class, com.aci.core.data.db.VerseNoteEntity.class, com.aci.core.data.db.PrayerRequestEntity.class, com.aci.core.data.db.TestimonyEntity.class, com.aci.core.data.db.EventEntity.class, com.aci.core.data.db.EventRegistrationEntity.class, com.aci.core.data.db.SongRequestEntity.class, com.aci.core.data.db.UserEntity.class, com.aci.core.data.db.ReadingPlanStateEntity.class, com.aci.core.data.db.ChapterReadEntity.class}, version = 9, exportSchema = false)
public abstract class AciDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DATABASE_NAME = "aci_church.db";
    @org.jetbrains.annotations.NotNull()
    public static final com.aci.core.data.db.AciDatabase.Companion Companion = null;
    
    public AciDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.aci.core.data.db.SongDao songDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.aci.core.data.db.BibleDao bibleDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.aci.core.data.db.PrayerDao prayerDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.aci.core.data.db.EventDao eventDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.aci.core.data.db.SongRequestDao songRequestDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.aci.core.data.db.UserDao userDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.aci.core.data.db.ReadingPlanDao readingPlanDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/aci/core/data/db/AciDatabase$Companion;", "", "()V", "DATABASE_NAME", "", "core-data_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}