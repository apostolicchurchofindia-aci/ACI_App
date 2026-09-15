package com.aci.core.data.db;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Boolean;
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
public final class BibleDao_Impl implements BibleDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<VerseBookmarkEntity> __insertionAdapterOfVerseBookmarkEntity;

  private final EntityInsertionAdapter<VerseNoteEntity> __insertionAdapterOfVerseNoteEntity;

  private final EntityInsertionAdapter<BibleBookEntity> __insertionAdapterOfBibleBookEntity;

  private final EntityInsertionAdapter<BibleVerseEntity> __insertionAdapterOfBibleVerseEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteBookmark;

  private final SharedSQLiteStatement __preparedStmtOfDeleteNote;

  public BibleDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVerseBookmarkEntity = new EntityInsertionAdapter<VerseBookmarkEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `verse_bookmarks` (`userId`,`verseId`,`createdAtEpochMillis`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VerseBookmarkEntity entity) {
        if (entity.getUserId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getUserId());
        }
        if (entity.getVerseId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getVerseId());
        }
        statement.bindLong(3, entity.getCreatedAtEpochMillis());
      }
    };
    this.__insertionAdapterOfVerseNoteEntity = new EntityInsertionAdapter<VerseNoteEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `verse_notes` (`userId`,`verseId`,`text`,`color`,`createdAtEpochMillis`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VerseNoteEntity entity) {
        if (entity.getUserId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getUserId());
        }
        if (entity.getVerseId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getVerseId());
        }
        if (entity.getText() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getText());
        }
        if (entity.getColor() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getColor());
        }
        statement.bindLong(5, entity.getCreatedAtEpochMillis());
      }
    };
    this.__insertionAdapterOfBibleBookEntity = new EntityInsertionAdapter<BibleBookEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `bible_books` (`id`,`testament`,`order`,`nameEn`,`nameTa`,`nameTe`,`numChapters`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BibleBookEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getTestament() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTestament());
        }
        statement.bindLong(3, entity.getOrder());
        if (entity.getNameEn() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getNameEn());
        }
        if (entity.getNameTa() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getNameTa());
        }
        if (entity.getNameTe() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getNameTe());
        }
        statement.bindLong(7, entity.getNumChapters());
      }
    };
    this.__insertionAdapterOfBibleVerseEntity = new EntityInsertionAdapter<BibleVerseEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `bible_verses` (`id`,`bookId`,`chapter`,`verseNumber`,`textEn`,`textTa`,`textTe`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BibleVerseEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getBookId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getBookId());
        }
        statement.bindLong(3, entity.getChapter());
        statement.bindLong(4, entity.getVerseNumber());
        if (entity.getTextEn() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTextEn());
        }
        if (entity.getTextTa() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getTextTa());
        }
        if (entity.getTextTe() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getTextTe());
        }
      }
    };
    this.__preparedStmtOfDeleteBookmark = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM verse_bookmarks WHERE userId = ? AND verseId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteNote = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM verse_notes WHERE userId = ? AND verseId = ? AND createdAtEpochMillis = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertBookmark(final VerseBookmarkEntity bookmark,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfVerseBookmarkEntity.insert(bookmark);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertNote(final VerseNoteEntity note,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfVerseNoteEntity.insert(note);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertBooks(final List<BibleBookEntity> books,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBibleBookEntity.insert(books);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertVerses(final List<BibleVerseEntity> verses,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBibleVerseEntity.insert(verses);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteBookmark(final String userId, final String verseId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteBookmark.acquire();
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, userId);
        }
        _argIndex = 2;
        if (verseId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, verseId);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteBookmark.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteNote(final String userId, final String verseId,
      final long createdAtEpochMillis, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteNote.acquire();
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, userId);
        }
        _argIndex = 2;
        if (verseId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, verseId);
        }
        _argIndex = 3;
        _stmt.bindLong(_argIndex, createdAtEpochMillis);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteNote.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<BibleBookEntity>> observeBooks() {
    final String _sql = "SELECT * FROM bible_books ORDER BY `order`";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"bible_books"}, new Callable<List<BibleBookEntity>>() {
      @Override
      @NonNull
      public List<BibleBookEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTestament = CursorUtil.getColumnIndexOrThrow(_cursor, "testament");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "order");
          final int _cursorIndexOfNameEn = CursorUtil.getColumnIndexOrThrow(_cursor, "nameEn");
          final int _cursorIndexOfNameTa = CursorUtil.getColumnIndexOrThrow(_cursor, "nameTa");
          final int _cursorIndexOfNameTe = CursorUtil.getColumnIndexOrThrow(_cursor, "nameTe");
          final int _cursorIndexOfNumChapters = CursorUtil.getColumnIndexOrThrow(_cursor, "numChapters");
          final List<BibleBookEntity> _result = new ArrayList<BibleBookEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BibleBookEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpTestament;
            if (_cursor.isNull(_cursorIndexOfTestament)) {
              _tmpTestament = null;
            } else {
              _tmpTestament = _cursor.getString(_cursorIndexOfTestament);
            }
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            final String _tmpNameEn;
            if (_cursor.isNull(_cursorIndexOfNameEn)) {
              _tmpNameEn = null;
            } else {
              _tmpNameEn = _cursor.getString(_cursorIndexOfNameEn);
            }
            final String _tmpNameTa;
            if (_cursor.isNull(_cursorIndexOfNameTa)) {
              _tmpNameTa = null;
            } else {
              _tmpNameTa = _cursor.getString(_cursorIndexOfNameTa);
            }
            final String _tmpNameTe;
            if (_cursor.isNull(_cursorIndexOfNameTe)) {
              _tmpNameTe = null;
            } else {
              _tmpNameTe = _cursor.getString(_cursorIndexOfNameTe);
            }
            final int _tmpNumChapters;
            _tmpNumChapters = _cursor.getInt(_cursorIndexOfNumChapters);
            _item = new BibleBookEntity(_tmpId,_tmpTestament,_tmpOrder,_tmpNameEn,_tmpNameTa,_tmpNameTe,_tmpNumChapters);
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
  public Flow<List<BibleVerseEntity>> observeVerses(final String bookId, final int chapter) {
    final String _sql = "SELECT * FROM bible_verses WHERE bookId = ? AND chapter = ? ORDER BY verseNumber";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (bookId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, bookId);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, chapter);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"bible_verses"}, new Callable<List<BibleVerseEntity>>() {
      @Override
      @NonNull
      public List<BibleVerseEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBookId = CursorUtil.getColumnIndexOrThrow(_cursor, "bookId");
          final int _cursorIndexOfChapter = CursorUtil.getColumnIndexOrThrow(_cursor, "chapter");
          final int _cursorIndexOfVerseNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "verseNumber");
          final int _cursorIndexOfTextEn = CursorUtil.getColumnIndexOrThrow(_cursor, "textEn");
          final int _cursorIndexOfTextTa = CursorUtil.getColumnIndexOrThrow(_cursor, "textTa");
          final int _cursorIndexOfTextTe = CursorUtil.getColumnIndexOrThrow(_cursor, "textTe");
          final List<BibleVerseEntity> _result = new ArrayList<BibleVerseEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BibleVerseEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpBookId;
            if (_cursor.isNull(_cursorIndexOfBookId)) {
              _tmpBookId = null;
            } else {
              _tmpBookId = _cursor.getString(_cursorIndexOfBookId);
            }
            final int _tmpChapter;
            _tmpChapter = _cursor.getInt(_cursorIndexOfChapter);
            final int _tmpVerseNumber;
            _tmpVerseNumber = _cursor.getInt(_cursorIndexOfVerseNumber);
            final String _tmpTextEn;
            if (_cursor.isNull(_cursorIndexOfTextEn)) {
              _tmpTextEn = null;
            } else {
              _tmpTextEn = _cursor.getString(_cursorIndexOfTextEn);
            }
            final String _tmpTextTa;
            if (_cursor.isNull(_cursorIndexOfTextTa)) {
              _tmpTextTa = null;
            } else {
              _tmpTextTa = _cursor.getString(_cursorIndexOfTextTa);
            }
            final String _tmpTextTe;
            if (_cursor.isNull(_cursorIndexOfTextTe)) {
              _tmpTextTe = null;
            } else {
              _tmpTextTe = _cursor.getString(_cursorIndexOfTextTe);
            }
            _item = new BibleVerseEntity(_tmpId,_tmpBookId,_tmpChapter,_tmpVerseNumber,_tmpTextEn,_tmpTextTa,_tmpTextTe);
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
  public Flow<List<BibleVerseEntity>> observeVerseRange(final String bookId, final int startChapter,
      final int endChapter) {
    final String _sql = "SELECT * FROM bible_verses WHERE bookId = ? AND chapter BETWEEN ? AND ? ORDER BY chapter, verseNumber";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (bookId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, bookId);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, startChapter);
    _argIndex = 3;
    _statement.bindLong(_argIndex, endChapter);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"bible_verses"}, new Callable<List<BibleVerseEntity>>() {
      @Override
      @NonNull
      public List<BibleVerseEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBookId = CursorUtil.getColumnIndexOrThrow(_cursor, "bookId");
          final int _cursorIndexOfChapter = CursorUtil.getColumnIndexOrThrow(_cursor, "chapter");
          final int _cursorIndexOfVerseNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "verseNumber");
          final int _cursorIndexOfTextEn = CursorUtil.getColumnIndexOrThrow(_cursor, "textEn");
          final int _cursorIndexOfTextTa = CursorUtil.getColumnIndexOrThrow(_cursor, "textTa");
          final int _cursorIndexOfTextTe = CursorUtil.getColumnIndexOrThrow(_cursor, "textTe");
          final List<BibleVerseEntity> _result = new ArrayList<BibleVerseEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BibleVerseEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpBookId;
            if (_cursor.isNull(_cursorIndexOfBookId)) {
              _tmpBookId = null;
            } else {
              _tmpBookId = _cursor.getString(_cursorIndexOfBookId);
            }
            final int _tmpChapter;
            _tmpChapter = _cursor.getInt(_cursorIndexOfChapter);
            final int _tmpVerseNumber;
            _tmpVerseNumber = _cursor.getInt(_cursorIndexOfVerseNumber);
            final String _tmpTextEn;
            if (_cursor.isNull(_cursorIndexOfTextEn)) {
              _tmpTextEn = null;
            } else {
              _tmpTextEn = _cursor.getString(_cursorIndexOfTextEn);
            }
            final String _tmpTextTa;
            if (_cursor.isNull(_cursorIndexOfTextTa)) {
              _tmpTextTa = null;
            } else {
              _tmpTextTa = _cursor.getString(_cursorIndexOfTextTa);
            }
            final String _tmpTextTe;
            if (_cursor.isNull(_cursorIndexOfTextTe)) {
              _tmpTextTe = null;
            } else {
              _tmpTextTe = _cursor.getString(_cursorIndexOfTextTe);
            }
            _item = new BibleVerseEntity(_tmpId,_tmpBookId,_tmpChapter,_tmpVerseNumber,_tmpTextEn,_tmpTextTa,_tmpTextTe);
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
  public Flow<BibleVerseEntity> observeVerse(final String verseId) {
    final String _sql = "SELECT * FROM bible_verses WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (verseId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, verseId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"bible_verses"}, new Callable<BibleVerseEntity>() {
      @Override
      @Nullable
      public BibleVerseEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBookId = CursorUtil.getColumnIndexOrThrow(_cursor, "bookId");
          final int _cursorIndexOfChapter = CursorUtil.getColumnIndexOrThrow(_cursor, "chapter");
          final int _cursorIndexOfVerseNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "verseNumber");
          final int _cursorIndexOfTextEn = CursorUtil.getColumnIndexOrThrow(_cursor, "textEn");
          final int _cursorIndexOfTextTa = CursorUtil.getColumnIndexOrThrow(_cursor, "textTa");
          final int _cursorIndexOfTextTe = CursorUtil.getColumnIndexOrThrow(_cursor, "textTe");
          final BibleVerseEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpBookId;
            if (_cursor.isNull(_cursorIndexOfBookId)) {
              _tmpBookId = null;
            } else {
              _tmpBookId = _cursor.getString(_cursorIndexOfBookId);
            }
            final int _tmpChapter;
            _tmpChapter = _cursor.getInt(_cursorIndexOfChapter);
            final int _tmpVerseNumber;
            _tmpVerseNumber = _cursor.getInt(_cursorIndexOfVerseNumber);
            final String _tmpTextEn;
            if (_cursor.isNull(_cursorIndexOfTextEn)) {
              _tmpTextEn = null;
            } else {
              _tmpTextEn = _cursor.getString(_cursorIndexOfTextEn);
            }
            final String _tmpTextTa;
            if (_cursor.isNull(_cursorIndexOfTextTa)) {
              _tmpTextTa = null;
            } else {
              _tmpTextTa = _cursor.getString(_cursorIndexOfTextTa);
            }
            final String _tmpTextTe;
            if (_cursor.isNull(_cursorIndexOfTextTe)) {
              _tmpTextTe = null;
            } else {
              _tmpTextTe = _cursor.getString(_cursorIndexOfTextTe);
            }
            _result = new BibleVerseEntity(_tmpId,_tmpBookId,_tmpChapter,_tmpVerseNumber,_tmpTextEn,_tmpTextTa,_tmpTextTe);
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
  public Flow<List<BibleVerseEntity>> searchVerses(final String query) {
    final String _sql = "SELECT * FROM bible_verses WHERE textEn LIKE '%' || ? || '%' LIMIT 100";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"bible_verses"}, new Callable<List<BibleVerseEntity>>() {
      @Override
      @NonNull
      public List<BibleVerseEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBookId = CursorUtil.getColumnIndexOrThrow(_cursor, "bookId");
          final int _cursorIndexOfChapter = CursorUtil.getColumnIndexOrThrow(_cursor, "chapter");
          final int _cursorIndexOfVerseNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "verseNumber");
          final int _cursorIndexOfTextEn = CursorUtil.getColumnIndexOrThrow(_cursor, "textEn");
          final int _cursorIndexOfTextTa = CursorUtil.getColumnIndexOrThrow(_cursor, "textTa");
          final int _cursorIndexOfTextTe = CursorUtil.getColumnIndexOrThrow(_cursor, "textTe");
          final List<BibleVerseEntity> _result = new ArrayList<BibleVerseEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BibleVerseEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpBookId;
            if (_cursor.isNull(_cursorIndexOfBookId)) {
              _tmpBookId = null;
            } else {
              _tmpBookId = _cursor.getString(_cursorIndexOfBookId);
            }
            final int _tmpChapter;
            _tmpChapter = _cursor.getInt(_cursorIndexOfChapter);
            final int _tmpVerseNumber;
            _tmpVerseNumber = _cursor.getInt(_cursorIndexOfVerseNumber);
            final String _tmpTextEn;
            if (_cursor.isNull(_cursorIndexOfTextEn)) {
              _tmpTextEn = null;
            } else {
              _tmpTextEn = _cursor.getString(_cursorIndexOfTextEn);
            }
            final String _tmpTextTa;
            if (_cursor.isNull(_cursorIndexOfTextTa)) {
              _tmpTextTa = null;
            } else {
              _tmpTextTa = _cursor.getString(_cursorIndexOfTextTa);
            }
            final String _tmpTextTe;
            if (_cursor.isNull(_cursorIndexOfTextTe)) {
              _tmpTextTe = null;
            } else {
              _tmpTextTe = _cursor.getString(_cursorIndexOfTextTe);
            }
            _item = new BibleVerseEntity(_tmpId,_tmpBookId,_tmpChapter,_tmpVerseNumber,_tmpTextEn,_tmpTextTa,_tmpTextTe);
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
  public Flow<List<VerseBookmarkEntity>> observeBookmarks(final String userId) {
    final String _sql = "SELECT * FROM verse_bookmarks WHERE userId = ? ORDER BY createdAtEpochMillis DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"verse_bookmarks"}, new Callable<List<VerseBookmarkEntity>>() {
      @Override
      @NonNull
      public List<VerseBookmarkEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfVerseId = CursorUtil.getColumnIndexOrThrow(_cursor, "verseId");
          final int _cursorIndexOfCreatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtEpochMillis");
          final List<VerseBookmarkEntity> _result = new ArrayList<VerseBookmarkEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VerseBookmarkEntity _item;
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpVerseId;
            if (_cursor.isNull(_cursorIndexOfVerseId)) {
              _tmpVerseId = null;
            } else {
              _tmpVerseId = _cursor.getString(_cursorIndexOfVerseId);
            }
            final long _tmpCreatedAtEpochMillis;
            _tmpCreatedAtEpochMillis = _cursor.getLong(_cursorIndexOfCreatedAtEpochMillis);
            _item = new VerseBookmarkEntity(_tmpUserId,_tmpVerseId,_tmpCreatedAtEpochMillis);
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
  public Flow<List<BibleVerseEntity>> observeBookmarkedVerses(final String userId) {
    final String _sql = "SELECT v.* FROM bible_verses v\n"
            + "           INNER JOIN verse_bookmarks b ON b.verseId = v.id\n"
            + "           WHERE b.userId = ? ORDER BY b.createdAtEpochMillis DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"bible_verses",
        "verse_bookmarks"}, new Callable<List<BibleVerseEntity>>() {
      @Override
      @NonNull
      public List<BibleVerseEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBookId = CursorUtil.getColumnIndexOrThrow(_cursor, "bookId");
          final int _cursorIndexOfChapter = CursorUtil.getColumnIndexOrThrow(_cursor, "chapter");
          final int _cursorIndexOfVerseNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "verseNumber");
          final int _cursorIndexOfTextEn = CursorUtil.getColumnIndexOrThrow(_cursor, "textEn");
          final int _cursorIndexOfTextTa = CursorUtil.getColumnIndexOrThrow(_cursor, "textTa");
          final int _cursorIndexOfTextTe = CursorUtil.getColumnIndexOrThrow(_cursor, "textTe");
          final List<BibleVerseEntity> _result = new ArrayList<BibleVerseEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BibleVerseEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpBookId;
            if (_cursor.isNull(_cursorIndexOfBookId)) {
              _tmpBookId = null;
            } else {
              _tmpBookId = _cursor.getString(_cursorIndexOfBookId);
            }
            final int _tmpChapter;
            _tmpChapter = _cursor.getInt(_cursorIndexOfChapter);
            final int _tmpVerseNumber;
            _tmpVerseNumber = _cursor.getInt(_cursorIndexOfVerseNumber);
            final String _tmpTextEn;
            if (_cursor.isNull(_cursorIndexOfTextEn)) {
              _tmpTextEn = null;
            } else {
              _tmpTextEn = _cursor.getString(_cursorIndexOfTextEn);
            }
            final String _tmpTextTa;
            if (_cursor.isNull(_cursorIndexOfTextTa)) {
              _tmpTextTa = null;
            } else {
              _tmpTextTa = _cursor.getString(_cursorIndexOfTextTa);
            }
            final String _tmpTextTe;
            if (_cursor.isNull(_cursorIndexOfTextTe)) {
              _tmpTextTe = null;
            } else {
              _tmpTextTe = _cursor.getString(_cursorIndexOfTextTe);
            }
            _item = new BibleVerseEntity(_tmpId,_tmpBookId,_tmpChapter,_tmpVerseNumber,_tmpTextEn,_tmpTextTa,_tmpTextTe);
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
  public Flow<List<VerseNoteEntity>> observeNotes(final String userId) {
    final String _sql = "SELECT * FROM verse_notes WHERE userId = ? ORDER BY createdAtEpochMillis DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"verse_notes"}, new Callable<List<VerseNoteEntity>>() {
      @Override
      @NonNull
      public List<VerseNoteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfVerseId = CursorUtil.getColumnIndexOrThrow(_cursor, "verseId");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfCreatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtEpochMillis");
          final List<VerseNoteEntity> _result = new ArrayList<VerseNoteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VerseNoteEntity _item;
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpVerseId;
            if (_cursor.isNull(_cursorIndexOfVerseId)) {
              _tmpVerseId = null;
            } else {
              _tmpVerseId = _cursor.getString(_cursorIndexOfVerseId);
            }
            final String _tmpText;
            if (_cursor.isNull(_cursorIndexOfText)) {
              _tmpText = null;
            } else {
              _tmpText = _cursor.getString(_cursorIndexOfText);
            }
            final String _tmpColor;
            if (_cursor.isNull(_cursorIndexOfColor)) {
              _tmpColor = null;
            } else {
              _tmpColor = _cursor.getString(_cursorIndexOfColor);
            }
            final long _tmpCreatedAtEpochMillis;
            _tmpCreatedAtEpochMillis = _cursor.getLong(_cursorIndexOfCreatedAtEpochMillis);
            _item = new VerseNoteEntity(_tmpUserId,_tmpVerseId,_tmpText,_tmpColor,_tmpCreatedAtEpochMillis);
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
  public Flow<List<VerseNoteEntity>> observeNotesForVerse(final String userId,
      final String verseId) {
    final String _sql = "SELECT * FROM verse_notes WHERE userId = ? AND verseId = ? ORDER BY createdAtEpochMillis DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    _argIndex = 2;
    if (verseId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, verseId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"verse_notes"}, new Callable<List<VerseNoteEntity>>() {
      @Override
      @NonNull
      public List<VerseNoteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfVerseId = CursorUtil.getColumnIndexOrThrow(_cursor, "verseId");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfCreatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtEpochMillis");
          final List<VerseNoteEntity> _result = new ArrayList<VerseNoteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VerseNoteEntity _item;
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpVerseId;
            if (_cursor.isNull(_cursorIndexOfVerseId)) {
              _tmpVerseId = null;
            } else {
              _tmpVerseId = _cursor.getString(_cursorIndexOfVerseId);
            }
            final String _tmpText;
            if (_cursor.isNull(_cursorIndexOfText)) {
              _tmpText = null;
            } else {
              _tmpText = _cursor.getString(_cursorIndexOfText);
            }
            final String _tmpColor;
            if (_cursor.isNull(_cursorIndexOfColor)) {
              _tmpColor = null;
            } else {
              _tmpColor = _cursor.getString(_cursorIndexOfColor);
            }
            final long _tmpCreatedAtEpochMillis;
            _tmpCreatedAtEpochMillis = _cursor.getLong(_cursorIndexOfCreatedAtEpochMillis);
            _item = new VerseNoteEntity(_tmpUserId,_tmpVerseId,_tmpText,_tmpColor,_tmpCreatedAtEpochMillis);
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
  public Object isBookmarked(final String userId, final String verseId,
      final Continuation<? super Boolean> $completion) {
    final String _sql = "SELECT EXISTS(SELECT 1 FROM verse_bookmarks WHERE userId = ? AND verseId = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    _argIndex = 2;
    if (verseId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, verseId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Boolean>() {
      @Override
      @NonNull
      public Boolean call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Boolean _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp == null ? null : _tmp != 0;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object countBooks(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM bible_books";
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
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
