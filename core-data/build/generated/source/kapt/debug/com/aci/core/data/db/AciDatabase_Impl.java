package com.aci.core.data.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AciDatabase_Impl extends AciDatabase {
  private volatile SongDao _songDao;

  private volatile BibleDao _bibleDao;

  private volatile PrayerDao _prayerDao;

  private volatile EventDao _eventDao;

  private volatile SongRequestDao _songRequestDao;

  private volatile UserDao _userDao;

  private volatile ReadingPlanDao _readingPlanDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(9) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `songs` (`id` TEXT NOT NULL, `titleEn` TEXT NOT NULL, `titleTa` TEXT NOT NULL, `titleTe` TEXT, `titleTeTranslit` TEXT NOT NULL, `titleTaTranslit` TEXT NOT NULL, `language` TEXT NOT NULL, `category` TEXT NOT NULL, `key` TEXT, `bpm` INTEGER, `composer` TEXT NOT NULL, `artist` TEXT NOT NULL, `artworkUrl` TEXT NOT NULL, `hasLyrics` INTEGER NOT NULL, `hasChords` INTEGER NOT NULL, `hasAudio` INTEGER NOT NULL, `songbookName` TEXT NOT NULL, `songbookNumber` INTEGER, `createdAtEpochMillis` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `song_lyric_sections` (`id` TEXT NOT NULL, `songId` TEXT NOT NULL, `language` TEXT NOT NULL, `sectionLabel` TEXT NOT NULL, `lyrics` TEXT NOT NULL, `translitLyrics` TEXT NOT NULL, `translationTa` TEXT NOT NULL, `orderIndex` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `song_media` (`songId` TEXT NOT NULL, `audioUrl` TEXT NOT NULL, `youtubeUrl` TEXT NOT NULL, `pdfUrl` TEXT NOT NULL, `presentationUrl` TEXT NOT NULL, `practiceUrl` TEXT NOT NULL, PRIMARY KEY(`songId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `bible_books` (`id` TEXT NOT NULL, `testament` TEXT NOT NULL, `order` INTEGER NOT NULL, `nameEn` TEXT NOT NULL, `nameTa` TEXT NOT NULL, `nameTe` TEXT NOT NULL, `numChapters` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `bible_verses` (`id` TEXT NOT NULL, `bookId` TEXT NOT NULL, `chapter` INTEGER NOT NULL, `verseNumber` INTEGER NOT NULL, `textEn` TEXT NOT NULL, `textTa` TEXT NOT NULL, `textTe` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_bible_verses_bookId_chapter` ON `bible_verses` (`bookId`, `chapter`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `verse_bookmarks` (`userId` TEXT NOT NULL, `verseId` TEXT NOT NULL, `createdAtEpochMillis` INTEGER NOT NULL, PRIMARY KEY(`userId`, `verseId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `verse_notes` (`userId` TEXT NOT NULL, `verseId` TEXT NOT NULL, `text` TEXT NOT NULL, `color` TEXT NOT NULL, `createdAtEpochMillis` INTEGER NOT NULL, PRIMARY KEY(`userId`, `verseId`, `createdAtEpochMillis`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `prayer_requests` (`id` TEXT NOT NULL, `userId` TEXT NOT NULL, `branchId` TEXT NOT NULL, `text` TEXT NOT NULL, `isAnonymous` INTEGER NOT NULL, `isPrivate` INTEGER NOT NULL, `prayedCount` INTEGER NOT NULL, `answered` INTEGER NOT NULL, `answeredAtEpochMillis` INTEGER, `createdAtEpochMillis` INTEGER NOT NULL, `status` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `testimonies` (`id` TEXT NOT NULL, `userId` TEXT NOT NULL, `branchId` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `mediaUrlsJson` TEXT NOT NULL, `isPublic` INTEGER NOT NULL, `approved` INTEGER NOT NULL, `createdAtEpochMillis` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `events` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `category` TEXT NOT NULL, `description` TEXT NOT NULL, `imageUrl` TEXT NOT NULL, `startAtEpochMillis` INTEGER, `endAtEpochMillis` INTEGER, `locationName` TEXT NOT NULL, `address` TEXT NOT NULL, `branchId` TEXT NOT NULL, `registrationRequired` INTEGER NOT NULL, `maxSeats` INTEGER NOT NULL, `qrRequired` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `event_registrations` (`id` TEXT NOT NULL, `eventId` TEXT NOT NULL, `userId` TEXT NOT NULL, `name` TEXT NOT NULL, `phone` TEXT NOT NULL, `seats` INTEGER NOT NULL, `registeredAtEpochMillis` INTEGER NOT NULL, `checkedInAtEpochMillis` INTEGER, `qrcodePayload` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `song_requests` (`id` TEXT NOT NULL, `titleEn` TEXT NOT NULL, `titleNative` TEXT NOT NULL, `language` TEXT NOT NULL, `notes` TEXT NOT NULL, `requestedByName` TEXT NOT NULL, `status` TEXT NOT NULL, `createdAtEpochMillis` INTEGER NOT NULL, `reviewedAtEpochMillis` INTEGER, `reviewNote` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `app_users` (`id` TEXT NOT NULL, `email` TEXT NOT NULL, `phone` TEXT NOT NULL, `role` TEXT NOT NULL, `fullName` TEXT NOT NULL, `createdAtEpochMillis` INTEGER NOT NULL, `pinHash` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `reading_plan_state` (`userId` TEXT NOT NULL, `startDateEpochDay` INTEGER NOT NULL, PRIMARY KEY(`userId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `chapter_reads` (`userId` TEXT NOT NULL, `bookId` TEXT NOT NULL, `chapter` INTEGER NOT NULL, `readAtEpochMillis` INTEGER NOT NULL, PRIMARY KEY(`userId`, `bookId`, `chapter`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '8a943ada7d9437da672dec950afa2083')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `songs`");
        db.execSQL("DROP TABLE IF EXISTS `song_lyric_sections`");
        db.execSQL("DROP TABLE IF EXISTS `song_media`");
        db.execSQL("DROP TABLE IF EXISTS `bible_books`");
        db.execSQL("DROP TABLE IF EXISTS `bible_verses`");
        db.execSQL("DROP TABLE IF EXISTS `verse_bookmarks`");
        db.execSQL("DROP TABLE IF EXISTS `verse_notes`");
        db.execSQL("DROP TABLE IF EXISTS `prayer_requests`");
        db.execSQL("DROP TABLE IF EXISTS `testimonies`");
        db.execSQL("DROP TABLE IF EXISTS `events`");
        db.execSQL("DROP TABLE IF EXISTS `event_registrations`");
        db.execSQL("DROP TABLE IF EXISTS `song_requests`");
        db.execSQL("DROP TABLE IF EXISTS `app_users`");
        db.execSQL("DROP TABLE IF EXISTS `reading_plan_state`");
        db.execSQL("DROP TABLE IF EXISTS `chapter_reads`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsSongs = new HashMap<String, TableInfo.Column>(19);
        _columnsSongs.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("titleEn", new TableInfo.Column("titleEn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("titleTa", new TableInfo.Column("titleTa", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("titleTe", new TableInfo.Column("titleTe", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("titleTeTranslit", new TableInfo.Column("titleTeTranslit", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("titleTaTranslit", new TableInfo.Column("titleTaTranslit", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("language", new TableInfo.Column("language", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("key", new TableInfo.Column("key", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("bpm", new TableInfo.Column("bpm", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("composer", new TableInfo.Column("composer", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("artist", new TableInfo.Column("artist", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("artworkUrl", new TableInfo.Column("artworkUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("hasLyrics", new TableInfo.Column("hasLyrics", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("hasChords", new TableInfo.Column("hasChords", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("hasAudio", new TableInfo.Column("hasAudio", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("songbookName", new TableInfo.Column("songbookName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("songbookNumber", new TableInfo.Column("songbookNumber", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("createdAtEpochMillis", new TableInfo.Column("createdAtEpochMillis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSongs = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSongs = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSongs = new TableInfo("songs", _columnsSongs, _foreignKeysSongs, _indicesSongs);
        final TableInfo _existingSongs = TableInfo.read(db, "songs");
        if (!_infoSongs.equals(_existingSongs)) {
          return new RoomOpenHelper.ValidationResult(false, "songs(com.aci.core.data.db.SongEntity).\n"
                  + " Expected:\n" + _infoSongs + "\n"
                  + " Found:\n" + _existingSongs);
        }
        final HashMap<String, TableInfo.Column> _columnsSongLyricSections = new HashMap<String, TableInfo.Column>(8);
        _columnsSongLyricSections.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongLyricSections.put("songId", new TableInfo.Column("songId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongLyricSections.put("language", new TableInfo.Column("language", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongLyricSections.put("sectionLabel", new TableInfo.Column("sectionLabel", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongLyricSections.put("lyrics", new TableInfo.Column("lyrics", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongLyricSections.put("translitLyrics", new TableInfo.Column("translitLyrics", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongLyricSections.put("translationTa", new TableInfo.Column("translationTa", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongLyricSections.put("orderIndex", new TableInfo.Column("orderIndex", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSongLyricSections = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSongLyricSections = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSongLyricSections = new TableInfo("song_lyric_sections", _columnsSongLyricSections, _foreignKeysSongLyricSections, _indicesSongLyricSections);
        final TableInfo _existingSongLyricSections = TableInfo.read(db, "song_lyric_sections");
        if (!_infoSongLyricSections.equals(_existingSongLyricSections)) {
          return new RoomOpenHelper.ValidationResult(false, "song_lyric_sections(com.aci.core.data.db.SongLyricSectionEntity).\n"
                  + " Expected:\n" + _infoSongLyricSections + "\n"
                  + " Found:\n" + _existingSongLyricSections);
        }
        final HashMap<String, TableInfo.Column> _columnsSongMedia = new HashMap<String, TableInfo.Column>(6);
        _columnsSongMedia.put("songId", new TableInfo.Column("songId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongMedia.put("audioUrl", new TableInfo.Column("audioUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongMedia.put("youtubeUrl", new TableInfo.Column("youtubeUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongMedia.put("pdfUrl", new TableInfo.Column("pdfUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongMedia.put("presentationUrl", new TableInfo.Column("presentationUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongMedia.put("practiceUrl", new TableInfo.Column("practiceUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSongMedia = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSongMedia = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSongMedia = new TableInfo("song_media", _columnsSongMedia, _foreignKeysSongMedia, _indicesSongMedia);
        final TableInfo _existingSongMedia = TableInfo.read(db, "song_media");
        if (!_infoSongMedia.equals(_existingSongMedia)) {
          return new RoomOpenHelper.ValidationResult(false, "song_media(com.aci.core.data.db.SongMediaEntity).\n"
                  + " Expected:\n" + _infoSongMedia + "\n"
                  + " Found:\n" + _existingSongMedia);
        }
        final HashMap<String, TableInfo.Column> _columnsBibleBooks = new HashMap<String, TableInfo.Column>(7);
        _columnsBibleBooks.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBibleBooks.put("testament", new TableInfo.Column("testament", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBibleBooks.put("order", new TableInfo.Column("order", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBibleBooks.put("nameEn", new TableInfo.Column("nameEn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBibleBooks.put("nameTa", new TableInfo.Column("nameTa", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBibleBooks.put("nameTe", new TableInfo.Column("nameTe", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBibleBooks.put("numChapters", new TableInfo.Column("numChapters", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBibleBooks = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBibleBooks = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBibleBooks = new TableInfo("bible_books", _columnsBibleBooks, _foreignKeysBibleBooks, _indicesBibleBooks);
        final TableInfo _existingBibleBooks = TableInfo.read(db, "bible_books");
        if (!_infoBibleBooks.equals(_existingBibleBooks)) {
          return new RoomOpenHelper.ValidationResult(false, "bible_books(com.aci.core.data.db.BibleBookEntity).\n"
                  + " Expected:\n" + _infoBibleBooks + "\n"
                  + " Found:\n" + _existingBibleBooks);
        }
        final HashMap<String, TableInfo.Column> _columnsBibleVerses = new HashMap<String, TableInfo.Column>(7);
        _columnsBibleVerses.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBibleVerses.put("bookId", new TableInfo.Column("bookId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBibleVerses.put("chapter", new TableInfo.Column("chapter", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBibleVerses.put("verseNumber", new TableInfo.Column("verseNumber", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBibleVerses.put("textEn", new TableInfo.Column("textEn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBibleVerses.put("textTa", new TableInfo.Column("textTa", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBibleVerses.put("textTe", new TableInfo.Column("textTe", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBibleVerses = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBibleVerses = new HashSet<TableInfo.Index>(1);
        _indicesBibleVerses.add(new TableInfo.Index("index_bible_verses_bookId_chapter", false, Arrays.asList("bookId", "chapter"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoBibleVerses = new TableInfo("bible_verses", _columnsBibleVerses, _foreignKeysBibleVerses, _indicesBibleVerses);
        final TableInfo _existingBibleVerses = TableInfo.read(db, "bible_verses");
        if (!_infoBibleVerses.equals(_existingBibleVerses)) {
          return new RoomOpenHelper.ValidationResult(false, "bible_verses(com.aci.core.data.db.BibleVerseEntity).\n"
                  + " Expected:\n" + _infoBibleVerses + "\n"
                  + " Found:\n" + _existingBibleVerses);
        }
        final HashMap<String, TableInfo.Column> _columnsVerseBookmarks = new HashMap<String, TableInfo.Column>(3);
        _columnsVerseBookmarks.put("userId", new TableInfo.Column("userId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVerseBookmarks.put("verseId", new TableInfo.Column("verseId", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVerseBookmarks.put("createdAtEpochMillis", new TableInfo.Column("createdAtEpochMillis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVerseBookmarks = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVerseBookmarks = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVerseBookmarks = new TableInfo("verse_bookmarks", _columnsVerseBookmarks, _foreignKeysVerseBookmarks, _indicesVerseBookmarks);
        final TableInfo _existingVerseBookmarks = TableInfo.read(db, "verse_bookmarks");
        if (!_infoVerseBookmarks.equals(_existingVerseBookmarks)) {
          return new RoomOpenHelper.ValidationResult(false, "verse_bookmarks(com.aci.core.data.db.VerseBookmarkEntity).\n"
                  + " Expected:\n" + _infoVerseBookmarks + "\n"
                  + " Found:\n" + _existingVerseBookmarks);
        }
        final HashMap<String, TableInfo.Column> _columnsVerseNotes = new HashMap<String, TableInfo.Column>(5);
        _columnsVerseNotes.put("userId", new TableInfo.Column("userId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVerseNotes.put("verseId", new TableInfo.Column("verseId", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVerseNotes.put("text", new TableInfo.Column("text", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVerseNotes.put("color", new TableInfo.Column("color", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVerseNotes.put("createdAtEpochMillis", new TableInfo.Column("createdAtEpochMillis", "INTEGER", true, 3, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVerseNotes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVerseNotes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVerseNotes = new TableInfo("verse_notes", _columnsVerseNotes, _foreignKeysVerseNotes, _indicesVerseNotes);
        final TableInfo _existingVerseNotes = TableInfo.read(db, "verse_notes");
        if (!_infoVerseNotes.equals(_existingVerseNotes)) {
          return new RoomOpenHelper.ValidationResult(false, "verse_notes(com.aci.core.data.db.VerseNoteEntity).\n"
                  + " Expected:\n" + _infoVerseNotes + "\n"
                  + " Found:\n" + _existingVerseNotes);
        }
        final HashMap<String, TableInfo.Column> _columnsPrayerRequests = new HashMap<String, TableInfo.Column>(11);
        _columnsPrayerRequests.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrayerRequests.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrayerRequests.put("branchId", new TableInfo.Column("branchId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrayerRequests.put("text", new TableInfo.Column("text", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrayerRequests.put("isAnonymous", new TableInfo.Column("isAnonymous", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrayerRequests.put("isPrivate", new TableInfo.Column("isPrivate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrayerRequests.put("prayedCount", new TableInfo.Column("prayedCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrayerRequests.put("answered", new TableInfo.Column("answered", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrayerRequests.put("answeredAtEpochMillis", new TableInfo.Column("answeredAtEpochMillis", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrayerRequests.put("createdAtEpochMillis", new TableInfo.Column("createdAtEpochMillis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrayerRequests.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPrayerRequests = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPrayerRequests = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPrayerRequests = new TableInfo("prayer_requests", _columnsPrayerRequests, _foreignKeysPrayerRequests, _indicesPrayerRequests);
        final TableInfo _existingPrayerRequests = TableInfo.read(db, "prayer_requests");
        if (!_infoPrayerRequests.equals(_existingPrayerRequests)) {
          return new RoomOpenHelper.ValidationResult(false, "prayer_requests(com.aci.core.data.db.PrayerRequestEntity).\n"
                  + " Expected:\n" + _infoPrayerRequests + "\n"
                  + " Found:\n" + _existingPrayerRequests);
        }
        final HashMap<String, TableInfo.Column> _columnsTestimonies = new HashMap<String, TableInfo.Column>(9);
        _columnsTestimonies.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTestimonies.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTestimonies.put("branchId", new TableInfo.Column("branchId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTestimonies.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTestimonies.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTestimonies.put("mediaUrlsJson", new TableInfo.Column("mediaUrlsJson", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTestimonies.put("isPublic", new TableInfo.Column("isPublic", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTestimonies.put("approved", new TableInfo.Column("approved", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTestimonies.put("createdAtEpochMillis", new TableInfo.Column("createdAtEpochMillis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTestimonies = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTestimonies = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTestimonies = new TableInfo("testimonies", _columnsTestimonies, _foreignKeysTestimonies, _indicesTestimonies);
        final TableInfo _existingTestimonies = TableInfo.read(db, "testimonies");
        if (!_infoTestimonies.equals(_existingTestimonies)) {
          return new RoomOpenHelper.ValidationResult(false, "testimonies(com.aci.core.data.db.TestimonyEntity).\n"
                  + " Expected:\n" + _infoTestimonies + "\n"
                  + " Found:\n" + _existingTestimonies);
        }
        final HashMap<String, TableInfo.Column> _columnsEvents = new HashMap<String, TableInfo.Column>(13);
        _columnsEvents.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("imageUrl", new TableInfo.Column("imageUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("startAtEpochMillis", new TableInfo.Column("startAtEpochMillis", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("endAtEpochMillis", new TableInfo.Column("endAtEpochMillis", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("locationName", new TableInfo.Column("locationName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("address", new TableInfo.Column("address", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("branchId", new TableInfo.Column("branchId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("registrationRequired", new TableInfo.Column("registrationRequired", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("maxSeats", new TableInfo.Column("maxSeats", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("qrRequired", new TableInfo.Column("qrRequired", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEvents = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEvents = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEvents = new TableInfo("events", _columnsEvents, _foreignKeysEvents, _indicesEvents);
        final TableInfo _existingEvents = TableInfo.read(db, "events");
        if (!_infoEvents.equals(_existingEvents)) {
          return new RoomOpenHelper.ValidationResult(false, "events(com.aci.core.data.db.EventEntity).\n"
                  + " Expected:\n" + _infoEvents + "\n"
                  + " Found:\n" + _existingEvents);
        }
        final HashMap<String, TableInfo.Column> _columnsEventRegistrations = new HashMap<String, TableInfo.Column>(9);
        _columnsEventRegistrations.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventRegistrations.put("eventId", new TableInfo.Column("eventId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventRegistrations.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventRegistrations.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventRegistrations.put("phone", new TableInfo.Column("phone", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventRegistrations.put("seats", new TableInfo.Column("seats", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventRegistrations.put("registeredAtEpochMillis", new TableInfo.Column("registeredAtEpochMillis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventRegistrations.put("checkedInAtEpochMillis", new TableInfo.Column("checkedInAtEpochMillis", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventRegistrations.put("qrcodePayload", new TableInfo.Column("qrcodePayload", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEventRegistrations = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEventRegistrations = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEventRegistrations = new TableInfo("event_registrations", _columnsEventRegistrations, _foreignKeysEventRegistrations, _indicesEventRegistrations);
        final TableInfo _existingEventRegistrations = TableInfo.read(db, "event_registrations");
        if (!_infoEventRegistrations.equals(_existingEventRegistrations)) {
          return new RoomOpenHelper.ValidationResult(false, "event_registrations(com.aci.core.data.db.EventRegistrationEntity).\n"
                  + " Expected:\n" + _infoEventRegistrations + "\n"
                  + " Found:\n" + _existingEventRegistrations);
        }
        final HashMap<String, TableInfo.Column> _columnsSongRequests = new HashMap<String, TableInfo.Column>(10);
        _columnsSongRequests.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongRequests.put("titleEn", new TableInfo.Column("titleEn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongRequests.put("titleNative", new TableInfo.Column("titleNative", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongRequests.put("language", new TableInfo.Column("language", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongRequests.put("notes", new TableInfo.Column("notes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongRequests.put("requestedByName", new TableInfo.Column("requestedByName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongRequests.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongRequests.put("createdAtEpochMillis", new TableInfo.Column("createdAtEpochMillis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongRequests.put("reviewedAtEpochMillis", new TableInfo.Column("reviewedAtEpochMillis", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongRequests.put("reviewNote", new TableInfo.Column("reviewNote", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSongRequests = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSongRequests = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSongRequests = new TableInfo("song_requests", _columnsSongRequests, _foreignKeysSongRequests, _indicesSongRequests);
        final TableInfo _existingSongRequests = TableInfo.read(db, "song_requests");
        if (!_infoSongRequests.equals(_existingSongRequests)) {
          return new RoomOpenHelper.ValidationResult(false, "song_requests(com.aci.core.data.db.SongRequestEntity).\n"
                  + " Expected:\n" + _infoSongRequests + "\n"
                  + " Found:\n" + _existingSongRequests);
        }
        final HashMap<String, TableInfo.Column> _columnsAppUsers = new HashMap<String, TableInfo.Column>(7);
        _columnsAppUsers.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppUsers.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppUsers.put("phone", new TableInfo.Column("phone", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppUsers.put("role", new TableInfo.Column("role", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppUsers.put("fullName", new TableInfo.Column("fullName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppUsers.put("createdAtEpochMillis", new TableInfo.Column("createdAtEpochMillis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppUsers.put("pinHash", new TableInfo.Column("pinHash", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAppUsers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAppUsers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAppUsers = new TableInfo("app_users", _columnsAppUsers, _foreignKeysAppUsers, _indicesAppUsers);
        final TableInfo _existingAppUsers = TableInfo.read(db, "app_users");
        if (!_infoAppUsers.equals(_existingAppUsers)) {
          return new RoomOpenHelper.ValidationResult(false, "app_users(com.aci.core.data.db.UserEntity).\n"
                  + " Expected:\n" + _infoAppUsers + "\n"
                  + " Found:\n" + _existingAppUsers);
        }
        final HashMap<String, TableInfo.Column> _columnsReadingPlanState = new HashMap<String, TableInfo.Column>(2);
        _columnsReadingPlanState.put("userId", new TableInfo.Column("userId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReadingPlanState.put("startDateEpochDay", new TableInfo.Column("startDateEpochDay", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReadingPlanState = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesReadingPlanState = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReadingPlanState = new TableInfo("reading_plan_state", _columnsReadingPlanState, _foreignKeysReadingPlanState, _indicesReadingPlanState);
        final TableInfo _existingReadingPlanState = TableInfo.read(db, "reading_plan_state");
        if (!_infoReadingPlanState.equals(_existingReadingPlanState)) {
          return new RoomOpenHelper.ValidationResult(false, "reading_plan_state(com.aci.core.data.db.ReadingPlanStateEntity).\n"
                  + " Expected:\n" + _infoReadingPlanState + "\n"
                  + " Found:\n" + _existingReadingPlanState);
        }
        final HashMap<String, TableInfo.Column> _columnsChapterReads = new HashMap<String, TableInfo.Column>(4);
        _columnsChapterReads.put("userId", new TableInfo.Column("userId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChapterReads.put("bookId", new TableInfo.Column("bookId", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChapterReads.put("chapter", new TableInfo.Column("chapter", "INTEGER", true, 3, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChapterReads.put("readAtEpochMillis", new TableInfo.Column("readAtEpochMillis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysChapterReads = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesChapterReads = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoChapterReads = new TableInfo("chapter_reads", _columnsChapterReads, _foreignKeysChapterReads, _indicesChapterReads);
        final TableInfo _existingChapterReads = TableInfo.read(db, "chapter_reads");
        if (!_infoChapterReads.equals(_existingChapterReads)) {
          return new RoomOpenHelper.ValidationResult(false, "chapter_reads(com.aci.core.data.db.ChapterReadEntity).\n"
                  + " Expected:\n" + _infoChapterReads + "\n"
                  + " Found:\n" + _existingChapterReads);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "8a943ada7d9437da672dec950afa2083", "b37f4225f6f4c2b6f7a12eb88619876a");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "songs","song_lyric_sections","song_media","bible_books","bible_verses","verse_bookmarks","verse_notes","prayer_requests","testimonies","events","event_registrations","song_requests","app_users","reading_plan_state","chapter_reads");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `songs`");
      _db.execSQL("DELETE FROM `song_lyric_sections`");
      _db.execSQL("DELETE FROM `song_media`");
      _db.execSQL("DELETE FROM `bible_books`");
      _db.execSQL("DELETE FROM `bible_verses`");
      _db.execSQL("DELETE FROM `verse_bookmarks`");
      _db.execSQL("DELETE FROM `verse_notes`");
      _db.execSQL("DELETE FROM `prayer_requests`");
      _db.execSQL("DELETE FROM `testimonies`");
      _db.execSQL("DELETE FROM `events`");
      _db.execSQL("DELETE FROM `event_registrations`");
      _db.execSQL("DELETE FROM `song_requests`");
      _db.execSQL("DELETE FROM `app_users`");
      _db.execSQL("DELETE FROM `reading_plan_state`");
      _db.execSQL("DELETE FROM `chapter_reads`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(SongDao.class, SongDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(BibleDao.class, BibleDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PrayerDao.class, PrayerDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(EventDao.class, EventDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SongRequestDao.class, SongRequestDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ReadingPlanDao.class, ReadingPlanDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public SongDao songDao() {
    if (_songDao != null) {
      return _songDao;
    } else {
      synchronized(this) {
        if(_songDao == null) {
          _songDao = new SongDao_Impl(this);
        }
        return _songDao;
      }
    }
  }

  @Override
  public BibleDao bibleDao() {
    if (_bibleDao != null) {
      return _bibleDao;
    } else {
      synchronized(this) {
        if(_bibleDao == null) {
          _bibleDao = new BibleDao_Impl(this);
        }
        return _bibleDao;
      }
    }
  }

  @Override
  public PrayerDao prayerDao() {
    if (_prayerDao != null) {
      return _prayerDao;
    } else {
      synchronized(this) {
        if(_prayerDao == null) {
          _prayerDao = new PrayerDao_Impl(this);
        }
        return _prayerDao;
      }
    }
  }

  @Override
  public EventDao eventDao() {
    if (_eventDao != null) {
      return _eventDao;
    } else {
      synchronized(this) {
        if(_eventDao == null) {
          _eventDao = new EventDao_Impl(this);
        }
        return _eventDao;
      }
    }
  }

  @Override
  public SongRequestDao songRequestDao() {
    if (_songRequestDao != null) {
      return _songRequestDao;
    } else {
      synchronized(this) {
        if(_songRequestDao == null) {
          _songRequestDao = new SongRequestDao_Impl(this);
        }
        return _songRequestDao;
      }
    }
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public ReadingPlanDao readingPlanDao() {
    if (_readingPlanDao != null) {
      return _readingPlanDao;
    } else {
      synchronized(this) {
        if(_readingPlanDao == null) {
          _readingPlanDao = new ReadingPlanDao_Impl(this);
        }
        return _readingPlanDao;
      }
    }
  }
}
