package com.aci.core.data.db;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class SongDao_Impl implements SongDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SongEntity> __insertionAdapterOfSongEntity;

  private final EntityInsertionAdapter<SongLyricSectionEntity> __insertionAdapterOfSongLyricSectionEntity;

  private final EntityInsertionAdapter<SongMediaEntity> __insertionAdapterOfSongMediaEntity;

  public SongDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSongEntity = new EntityInsertionAdapter<SongEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `songs` (`id`,`titleEn`,`titleTa`,`titleTe`,`titleTeTranslit`,`titleTaTranslit`,`language`,`category`,`key`,`bpm`,`composer`,`artist`,`artworkUrl`,`hasLyrics`,`hasChords`,`hasAudio`,`songbookName`,`songbookNumber`,`createdAtEpochMillis`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SongEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getTitleEn() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitleEn());
        }
        if (entity.getTitleTa() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTitleTa());
        }
        if (entity.getTitleTe() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTitleTe());
        }
        if (entity.getTitleTeTranslit() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTitleTeTranslit());
        }
        if (entity.getTitleTaTranslit() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getTitleTaTranslit());
        }
        if (entity.getLanguage() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getLanguage());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getCategory());
        }
        if (entity.getKey() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getKey());
        }
        if (entity.getBpm() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getBpm());
        }
        if (entity.getComposer() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getComposer());
        }
        if (entity.getArtist() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getArtist());
        }
        if (entity.getArtworkUrl() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getArtworkUrl());
        }
        final int _tmp = entity.getHasLyrics() ? 1 : 0;
        statement.bindLong(14, _tmp);
        final int _tmp_1 = entity.getHasChords() ? 1 : 0;
        statement.bindLong(15, _tmp_1);
        final int _tmp_2 = entity.getHasAudio() ? 1 : 0;
        statement.bindLong(16, _tmp_2);
        if (entity.getSongbookName() == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, entity.getSongbookName());
        }
        if (entity.getSongbookNumber() == null) {
          statement.bindNull(18);
        } else {
          statement.bindLong(18, entity.getSongbookNumber());
        }
        statement.bindLong(19, entity.getCreatedAtEpochMillis());
      }
    };
    this.__insertionAdapterOfSongLyricSectionEntity = new EntityInsertionAdapter<SongLyricSectionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `song_lyric_sections` (`id`,`songId`,`language`,`sectionLabel`,`lyrics`,`translitLyrics`,`translationTa`,`orderIndex`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SongLyricSectionEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getSongId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getSongId());
        }
        if (entity.getLanguage() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getLanguage());
        }
        if (entity.getSectionLabel() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getSectionLabel());
        }
        if (entity.getLyrics() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getLyrics());
        }
        if (entity.getTranslitLyrics() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getTranslitLyrics());
        }
        if (entity.getTranslationTa() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getTranslationTa());
        }
        statement.bindLong(8, entity.getOrderIndex());
      }
    };
    this.__insertionAdapterOfSongMediaEntity = new EntityInsertionAdapter<SongMediaEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `song_media` (`songId`,`audioUrl`,`youtubeUrl`,`pdfUrl`,`presentationUrl`,`practiceUrl`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SongMediaEntity entity) {
        if (entity.getSongId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getSongId());
        }
        if (entity.getAudioUrl() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getAudioUrl());
        }
        if (entity.getYoutubeUrl() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getYoutubeUrl());
        }
        if (entity.getPdfUrl() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPdfUrl());
        }
        if (entity.getPresentationUrl() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getPresentationUrl());
        }
        if (entity.getPracticeUrl() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getPracticeUrl());
        }
      }
    };
  }

  @Override
  public Object insertSongs(final List<SongEntity> songs, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSongEntity.insert(songs);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object insertLyrics(final List<SongLyricSectionEntity> lyrics,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSongLyricSectionEntity.insert(lyrics);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object insertMedia(final List<SongMediaEntity> media,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSongMediaEntity.insert(media);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Flow<List<SongEntity>> observeSongs() {
    final String _sql = "SELECT * FROM songs ORDER BY titleEn";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"songs"}, new Callable<List<SongEntity>>() {
      @Override
      @NonNull
      public List<SongEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitleEn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleEn");
          final int _cursorIndexOfTitleTa = CursorUtil.getColumnIndexOrThrow(_cursor, "titleTa");
          final int _cursorIndexOfTitleTe = CursorUtil.getColumnIndexOrThrow(_cursor, "titleTe");
          final int _cursorIndexOfTitleTeTranslit = CursorUtil.getColumnIndexOrThrow(_cursor, "titleTeTranslit");
          final int _cursorIndexOfTitleTaTranslit = CursorUtil.getColumnIndexOrThrow(_cursor, "titleTaTranslit");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfKey = CursorUtil.getColumnIndexOrThrow(_cursor, "key");
          final int _cursorIndexOfBpm = CursorUtil.getColumnIndexOrThrow(_cursor, "bpm");
          final int _cursorIndexOfComposer = CursorUtil.getColumnIndexOrThrow(_cursor, "composer");
          final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
          final int _cursorIndexOfArtworkUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "artworkUrl");
          final int _cursorIndexOfHasLyrics = CursorUtil.getColumnIndexOrThrow(_cursor, "hasLyrics");
          final int _cursorIndexOfHasChords = CursorUtil.getColumnIndexOrThrow(_cursor, "hasChords");
          final int _cursorIndexOfHasAudio = CursorUtil.getColumnIndexOrThrow(_cursor, "hasAudio");
          final int _cursorIndexOfSongbookName = CursorUtil.getColumnIndexOrThrow(_cursor, "songbookName");
          final int _cursorIndexOfSongbookNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "songbookNumber");
          final int _cursorIndexOfCreatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtEpochMillis");
          final List<SongEntity> _result = new ArrayList<SongEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SongEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpTitleEn;
            if (_cursor.isNull(_cursorIndexOfTitleEn)) {
              _tmpTitleEn = null;
            } else {
              _tmpTitleEn = _cursor.getString(_cursorIndexOfTitleEn);
            }
            final String _tmpTitleTa;
            if (_cursor.isNull(_cursorIndexOfTitleTa)) {
              _tmpTitleTa = null;
            } else {
              _tmpTitleTa = _cursor.getString(_cursorIndexOfTitleTa);
            }
            final String _tmpTitleTe;
            if (_cursor.isNull(_cursorIndexOfTitleTe)) {
              _tmpTitleTe = null;
            } else {
              _tmpTitleTe = _cursor.getString(_cursorIndexOfTitleTe);
            }
            final String _tmpTitleTeTranslit;
            if (_cursor.isNull(_cursorIndexOfTitleTeTranslit)) {
              _tmpTitleTeTranslit = null;
            } else {
              _tmpTitleTeTranslit = _cursor.getString(_cursorIndexOfTitleTeTranslit);
            }
            final String _tmpTitleTaTranslit;
            if (_cursor.isNull(_cursorIndexOfTitleTaTranslit)) {
              _tmpTitleTaTranslit = null;
            } else {
              _tmpTitleTaTranslit = _cursor.getString(_cursorIndexOfTitleTaTranslit);
            }
            final String _tmpLanguage;
            if (_cursor.isNull(_cursorIndexOfLanguage)) {
              _tmpLanguage = null;
            } else {
              _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpKey;
            if (_cursor.isNull(_cursorIndexOfKey)) {
              _tmpKey = null;
            } else {
              _tmpKey = _cursor.getString(_cursorIndexOfKey);
            }
            final Integer _tmpBpm;
            if (_cursor.isNull(_cursorIndexOfBpm)) {
              _tmpBpm = null;
            } else {
              _tmpBpm = _cursor.getInt(_cursorIndexOfBpm);
            }
            final String _tmpComposer;
            if (_cursor.isNull(_cursorIndexOfComposer)) {
              _tmpComposer = null;
            } else {
              _tmpComposer = _cursor.getString(_cursorIndexOfComposer);
            }
            final String _tmpArtist;
            if (_cursor.isNull(_cursorIndexOfArtist)) {
              _tmpArtist = null;
            } else {
              _tmpArtist = _cursor.getString(_cursorIndexOfArtist);
            }
            final String _tmpArtworkUrl;
            if (_cursor.isNull(_cursorIndexOfArtworkUrl)) {
              _tmpArtworkUrl = null;
            } else {
              _tmpArtworkUrl = _cursor.getString(_cursorIndexOfArtworkUrl);
            }
            final boolean _tmpHasLyrics;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasLyrics);
            _tmpHasLyrics = _tmp != 0;
            final boolean _tmpHasChords;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfHasChords);
            _tmpHasChords = _tmp_1 != 0;
            final boolean _tmpHasAudio;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfHasAudio);
            _tmpHasAudio = _tmp_2 != 0;
            final String _tmpSongbookName;
            if (_cursor.isNull(_cursorIndexOfSongbookName)) {
              _tmpSongbookName = null;
            } else {
              _tmpSongbookName = _cursor.getString(_cursorIndexOfSongbookName);
            }
            final Integer _tmpSongbookNumber;
            if (_cursor.isNull(_cursorIndexOfSongbookNumber)) {
              _tmpSongbookNumber = null;
            } else {
              _tmpSongbookNumber = _cursor.getInt(_cursorIndexOfSongbookNumber);
            }
            final long _tmpCreatedAtEpochMillis;
            _tmpCreatedAtEpochMillis = _cursor.getLong(_cursorIndexOfCreatedAtEpochMillis);
            _item = new SongEntity(_tmpId,_tmpTitleEn,_tmpTitleTa,_tmpTitleTe,_tmpTitleTeTranslit,_tmpTitleTaTranslit,_tmpLanguage,_tmpCategory,_tmpKey,_tmpBpm,_tmpComposer,_tmpArtist,_tmpArtworkUrl,_tmpHasLyrics,_tmpHasChords,_tmpHasAudio,_tmpSongbookName,_tmpSongbookNumber,_tmpCreatedAtEpochMillis);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<SongEntity> observeSong(final String id) {
    final String _sql = "SELECT * FROM songs WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"songs"}, new Callable<SongEntity>() {
      @Override
      @Nullable
      public SongEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitleEn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleEn");
          final int _cursorIndexOfTitleTa = CursorUtil.getColumnIndexOrThrow(_cursor, "titleTa");
          final int _cursorIndexOfTitleTe = CursorUtil.getColumnIndexOrThrow(_cursor, "titleTe");
          final int _cursorIndexOfTitleTeTranslit = CursorUtil.getColumnIndexOrThrow(_cursor, "titleTeTranslit");
          final int _cursorIndexOfTitleTaTranslit = CursorUtil.getColumnIndexOrThrow(_cursor, "titleTaTranslit");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfKey = CursorUtil.getColumnIndexOrThrow(_cursor, "key");
          final int _cursorIndexOfBpm = CursorUtil.getColumnIndexOrThrow(_cursor, "bpm");
          final int _cursorIndexOfComposer = CursorUtil.getColumnIndexOrThrow(_cursor, "composer");
          final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
          final int _cursorIndexOfArtworkUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "artworkUrl");
          final int _cursorIndexOfHasLyrics = CursorUtil.getColumnIndexOrThrow(_cursor, "hasLyrics");
          final int _cursorIndexOfHasChords = CursorUtil.getColumnIndexOrThrow(_cursor, "hasChords");
          final int _cursorIndexOfHasAudio = CursorUtil.getColumnIndexOrThrow(_cursor, "hasAudio");
          final int _cursorIndexOfSongbookName = CursorUtil.getColumnIndexOrThrow(_cursor, "songbookName");
          final int _cursorIndexOfSongbookNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "songbookNumber");
          final int _cursorIndexOfCreatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtEpochMillis");
          final SongEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpTitleEn;
            if (_cursor.isNull(_cursorIndexOfTitleEn)) {
              _tmpTitleEn = null;
            } else {
              _tmpTitleEn = _cursor.getString(_cursorIndexOfTitleEn);
            }
            final String _tmpTitleTa;
            if (_cursor.isNull(_cursorIndexOfTitleTa)) {
              _tmpTitleTa = null;
            } else {
              _tmpTitleTa = _cursor.getString(_cursorIndexOfTitleTa);
            }
            final String _tmpTitleTe;
            if (_cursor.isNull(_cursorIndexOfTitleTe)) {
              _tmpTitleTe = null;
            } else {
              _tmpTitleTe = _cursor.getString(_cursorIndexOfTitleTe);
            }
            final String _tmpTitleTeTranslit;
            if (_cursor.isNull(_cursorIndexOfTitleTeTranslit)) {
              _tmpTitleTeTranslit = null;
            } else {
              _tmpTitleTeTranslit = _cursor.getString(_cursorIndexOfTitleTeTranslit);
            }
            final String _tmpTitleTaTranslit;
            if (_cursor.isNull(_cursorIndexOfTitleTaTranslit)) {
              _tmpTitleTaTranslit = null;
            } else {
              _tmpTitleTaTranslit = _cursor.getString(_cursorIndexOfTitleTaTranslit);
            }
            final String _tmpLanguage;
            if (_cursor.isNull(_cursorIndexOfLanguage)) {
              _tmpLanguage = null;
            } else {
              _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpKey;
            if (_cursor.isNull(_cursorIndexOfKey)) {
              _tmpKey = null;
            } else {
              _tmpKey = _cursor.getString(_cursorIndexOfKey);
            }
            final Integer _tmpBpm;
            if (_cursor.isNull(_cursorIndexOfBpm)) {
              _tmpBpm = null;
            } else {
              _tmpBpm = _cursor.getInt(_cursorIndexOfBpm);
            }
            final String _tmpComposer;
            if (_cursor.isNull(_cursorIndexOfComposer)) {
              _tmpComposer = null;
            } else {
              _tmpComposer = _cursor.getString(_cursorIndexOfComposer);
            }
            final String _tmpArtist;
            if (_cursor.isNull(_cursorIndexOfArtist)) {
              _tmpArtist = null;
            } else {
              _tmpArtist = _cursor.getString(_cursorIndexOfArtist);
            }
            final String _tmpArtworkUrl;
            if (_cursor.isNull(_cursorIndexOfArtworkUrl)) {
              _tmpArtworkUrl = null;
            } else {
              _tmpArtworkUrl = _cursor.getString(_cursorIndexOfArtworkUrl);
            }
            final boolean _tmpHasLyrics;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasLyrics);
            _tmpHasLyrics = _tmp != 0;
            final boolean _tmpHasChords;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfHasChords);
            _tmpHasChords = _tmp_1 != 0;
            final boolean _tmpHasAudio;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfHasAudio);
            _tmpHasAudio = _tmp_2 != 0;
            final String _tmpSongbookName;
            if (_cursor.isNull(_cursorIndexOfSongbookName)) {
              _tmpSongbookName = null;
            } else {
              _tmpSongbookName = _cursor.getString(_cursorIndexOfSongbookName);
            }
            final Integer _tmpSongbookNumber;
            if (_cursor.isNull(_cursorIndexOfSongbookNumber)) {
              _tmpSongbookNumber = null;
            } else {
              _tmpSongbookNumber = _cursor.getInt(_cursorIndexOfSongbookNumber);
            }
            final long _tmpCreatedAtEpochMillis;
            _tmpCreatedAtEpochMillis = _cursor.getLong(_cursorIndexOfCreatedAtEpochMillis);
            _result = new SongEntity(_tmpId,_tmpTitleEn,_tmpTitleTa,_tmpTitleTe,_tmpTitleTeTranslit,_tmpTitleTaTranslit,_tmpLanguage,_tmpCategory,_tmpKey,_tmpBpm,_tmpComposer,_tmpArtist,_tmpArtworkUrl,_tmpHasLyrics,_tmpHasChords,_tmpHasAudio,_tmpSongbookName,_tmpSongbookNumber,_tmpCreatedAtEpochMillis);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<SongLyricSectionEntity>> observeLyrics(final String songId) {
    final String _sql = "SELECT * FROM song_lyric_sections WHERE songId = ? ORDER BY orderIndex";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (songId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, songId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"song_lyric_sections"}, new Callable<List<SongLyricSectionEntity>>() {
      @Override
      @NonNull
      public List<SongLyricSectionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSongId = CursorUtil.getColumnIndexOrThrow(_cursor, "songId");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfSectionLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "sectionLabel");
          final int _cursorIndexOfLyrics = CursorUtil.getColumnIndexOrThrow(_cursor, "lyrics");
          final int _cursorIndexOfTranslitLyrics = CursorUtil.getColumnIndexOrThrow(_cursor, "translitLyrics");
          final int _cursorIndexOfTranslationTa = CursorUtil.getColumnIndexOrThrow(_cursor, "translationTa");
          final int _cursorIndexOfOrderIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "orderIndex");
          final List<SongLyricSectionEntity> _result = new ArrayList<SongLyricSectionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SongLyricSectionEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpSongId;
            if (_cursor.isNull(_cursorIndexOfSongId)) {
              _tmpSongId = null;
            } else {
              _tmpSongId = _cursor.getString(_cursorIndexOfSongId);
            }
            final String _tmpLanguage;
            if (_cursor.isNull(_cursorIndexOfLanguage)) {
              _tmpLanguage = null;
            } else {
              _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            }
            final String _tmpSectionLabel;
            if (_cursor.isNull(_cursorIndexOfSectionLabel)) {
              _tmpSectionLabel = null;
            } else {
              _tmpSectionLabel = _cursor.getString(_cursorIndexOfSectionLabel);
            }
            final String _tmpLyrics;
            if (_cursor.isNull(_cursorIndexOfLyrics)) {
              _tmpLyrics = null;
            } else {
              _tmpLyrics = _cursor.getString(_cursorIndexOfLyrics);
            }
            final String _tmpTranslitLyrics;
            if (_cursor.isNull(_cursorIndexOfTranslitLyrics)) {
              _tmpTranslitLyrics = null;
            } else {
              _tmpTranslitLyrics = _cursor.getString(_cursorIndexOfTranslitLyrics);
            }
            final String _tmpTranslationTa;
            if (_cursor.isNull(_cursorIndexOfTranslationTa)) {
              _tmpTranslationTa = null;
            } else {
              _tmpTranslationTa = _cursor.getString(_cursorIndexOfTranslationTa);
            }
            final int _tmpOrderIndex;
            _tmpOrderIndex = _cursor.getInt(_cursorIndexOfOrderIndex);
            _item = new SongLyricSectionEntity(_tmpId,_tmpSongId,_tmpLanguage,_tmpSectionLabel,_tmpLyrics,_tmpTranslitLyrics,_tmpTranslationTa,_tmpOrderIndex);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<SongMediaEntity> observeMedia(final String songId) {
    final String _sql = "SELECT * FROM song_media WHERE songId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (songId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, songId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"song_media"}, new Callable<SongMediaEntity>() {
      @Override
      @Nullable
      public SongMediaEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSongId = CursorUtil.getColumnIndexOrThrow(_cursor, "songId");
          final int _cursorIndexOfAudioUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "audioUrl");
          final int _cursorIndexOfYoutubeUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "youtubeUrl");
          final int _cursorIndexOfPdfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "pdfUrl");
          final int _cursorIndexOfPresentationUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "presentationUrl");
          final int _cursorIndexOfPracticeUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "practiceUrl");
          final SongMediaEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpSongId;
            if (_cursor.isNull(_cursorIndexOfSongId)) {
              _tmpSongId = null;
            } else {
              _tmpSongId = _cursor.getString(_cursorIndexOfSongId);
            }
            final String _tmpAudioUrl;
            if (_cursor.isNull(_cursorIndexOfAudioUrl)) {
              _tmpAudioUrl = null;
            } else {
              _tmpAudioUrl = _cursor.getString(_cursorIndexOfAudioUrl);
            }
            final String _tmpYoutubeUrl;
            if (_cursor.isNull(_cursorIndexOfYoutubeUrl)) {
              _tmpYoutubeUrl = null;
            } else {
              _tmpYoutubeUrl = _cursor.getString(_cursorIndexOfYoutubeUrl);
            }
            final String _tmpPdfUrl;
            if (_cursor.isNull(_cursorIndexOfPdfUrl)) {
              _tmpPdfUrl = null;
            } else {
              _tmpPdfUrl = _cursor.getString(_cursorIndexOfPdfUrl);
            }
            final String _tmpPresentationUrl;
            if (_cursor.isNull(_cursorIndexOfPresentationUrl)) {
              _tmpPresentationUrl = null;
            } else {
              _tmpPresentationUrl = _cursor.getString(_cursorIndexOfPresentationUrl);
            }
            final String _tmpPracticeUrl;
            if (_cursor.isNull(_cursorIndexOfPracticeUrl)) {
              _tmpPracticeUrl = null;
            } else {
              _tmpPracticeUrl = _cursor.getString(_cursorIndexOfPracticeUrl);
            }
            _result = new SongMediaEntity(_tmpSongId,_tmpAudioUrl,_tmpYoutubeUrl,_tmpPdfUrl,_tmpPresentationUrl,_tmpPracticeUrl);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object countSongs(final Continuation<? super Integer> arg0) {
    final String _sql = "SELECT COUNT(*) FROM songs";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg0);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
