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
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
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
public final class SongRequestDao_Impl implements SongRequestDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SongRequestEntity> __insertionAdapterOfSongRequestEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateStatus;

  public SongRequestDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSongRequestEntity = new EntityInsertionAdapter<SongRequestEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `song_requests` (`id`,`titleEn`,`titleNative`,`language`,`notes`,`requestedByName`,`status`,`createdAtEpochMillis`,`reviewedAtEpochMillis`,`reviewNote`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SongRequestEntity entity) {
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
        if (entity.getTitleNative() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTitleNative());
        }
        if (entity.getLanguage() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getLanguage());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getNotes());
        }
        if (entity.getRequestedByName() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getRequestedByName());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getStatus());
        }
        statement.bindLong(8, entity.getCreatedAtEpochMillis());
        if (entity.getReviewedAtEpochMillis() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getReviewedAtEpochMillis());
        }
        if (entity.getReviewNote() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getReviewNote());
        }
      }
    };
    this.__preparedStmtOfUpdateStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE song_requests SET status = ?, reviewedAtEpochMillis = ?, reviewNote = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final SongRequestEntity request, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSongRequestEntity.insert(request);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object updateStatus(final String id, final String status, final long reviewedAt,
      final String reviewNote, final Continuation<? super Unit> arg4) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateStatus.acquire();
        int _argIndex = 1;
        if (status == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, status);
        }
        _argIndex = 2;
        _stmt.bindLong(_argIndex, reviewedAt);
        _argIndex = 3;
        if (reviewNote == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, reviewNote);
        }
        _argIndex = 4;
        if (id == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, id);
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
          __preparedStmtOfUpdateStatus.release(_stmt);
        }
      }
    }, arg4);
  }

  @Override
  public Flow<List<SongRequestEntity>> observePending() {
    final String _sql = "SELECT * FROM song_requests WHERE status = 'PENDING' ORDER BY createdAtEpochMillis ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"song_requests"}, new Callable<List<SongRequestEntity>>() {
      @Override
      @NonNull
      public List<SongRequestEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitleEn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleEn");
          final int _cursorIndexOfTitleNative = CursorUtil.getColumnIndexOrThrow(_cursor, "titleNative");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfRequestedByName = CursorUtil.getColumnIndexOrThrow(_cursor, "requestedByName");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtEpochMillis");
          final int _cursorIndexOfReviewedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewedAtEpochMillis");
          final int _cursorIndexOfReviewNote = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewNote");
          final List<SongRequestEntity> _result = new ArrayList<SongRequestEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SongRequestEntity _item;
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
            final String _tmpTitleNative;
            if (_cursor.isNull(_cursorIndexOfTitleNative)) {
              _tmpTitleNative = null;
            } else {
              _tmpTitleNative = _cursor.getString(_cursorIndexOfTitleNative);
            }
            final String _tmpLanguage;
            if (_cursor.isNull(_cursorIndexOfLanguage)) {
              _tmpLanguage = null;
            } else {
              _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpRequestedByName;
            if (_cursor.isNull(_cursorIndexOfRequestedByName)) {
              _tmpRequestedByName = null;
            } else {
              _tmpRequestedByName = _cursor.getString(_cursorIndexOfRequestedByName);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final long _tmpCreatedAtEpochMillis;
            _tmpCreatedAtEpochMillis = _cursor.getLong(_cursorIndexOfCreatedAtEpochMillis);
            final Long _tmpReviewedAtEpochMillis;
            if (_cursor.isNull(_cursorIndexOfReviewedAtEpochMillis)) {
              _tmpReviewedAtEpochMillis = null;
            } else {
              _tmpReviewedAtEpochMillis = _cursor.getLong(_cursorIndexOfReviewedAtEpochMillis);
            }
            final String _tmpReviewNote;
            if (_cursor.isNull(_cursorIndexOfReviewNote)) {
              _tmpReviewNote = null;
            } else {
              _tmpReviewNote = _cursor.getString(_cursorIndexOfReviewNote);
            }
            _item = new SongRequestEntity(_tmpId,_tmpTitleEn,_tmpTitleNative,_tmpLanguage,_tmpNotes,_tmpRequestedByName,_tmpStatus,_tmpCreatedAtEpochMillis,_tmpReviewedAtEpochMillis,_tmpReviewNote);
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
  public Flow<List<SongRequestEntity>> observeAll() {
    final String _sql = "SELECT * FROM song_requests ORDER BY createdAtEpochMillis DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"song_requests"}, new Callable<List<SongRequestEntity>>() {
      @Override
      @NonNull
      public List<SongRequestEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitleEn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleEn");
          final int _cursorIndexOfTitleNative = CursorUtil.getColumnIndexOrThrow(_cursor, "titleNative");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfRequestedByName = CursorUtil.getColumnIndexOrThrow(_cursor, "requestedByName");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtEpochMillis");
          final int _cursorIndexOfReviewedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewedAtEpochMillis");
          final int _cursorIndexOfReviewNote = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewNote");
          final List<SongRequestEntity> _result = new ArrayList<SongRequestEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SongRequestEntity _item;
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
            final String _tmpTitleNative;
            if (_cursor.isNull(_cursorIndexOfTitleNative)) {
              _tmpTitleNative = null;
            } else {
              _tmpTitleNative = _cursor.getString(_cursorIndexOfTitleNative);
            }
            final String _tmpLanguage;
            if (_cursor.isNull(_cursorIndexOfLanguage)) {
              _tmpLanguage = null;
            } else {
              _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpRequestedByName;
            if (_cursor.isNull(_cursorIndexOfRequestedByName)) {
              _tmpRequestedByName = null;
            } else {
              _tmpRequestedByName = _cursor.getString(_cursorIndexOfRequestedByName);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final long _tmpCreatedAtEpochMillis;
            _tmpCreatedAtEpochMillis = _cursor.getLong(_cursorIndexOfCreatedAtEpochMillis);
            final Long _tmpReviewedAtEpochMillis;
            if (_cursor.isNull(_cursorIndexOfReviewedAtEpochMillis)) {
              _tmpReviewedAtEpochMillis = null;
            } else {
              _tmpReviewedAtEpochMillis = _cursor.getLong(_cursorIndexOfReviewedAtEpochMillis);
            }
            final String _tmpReviewNote;
            if (_cursor.isNull(_cursorIndexOfReviewNote)) {
              _tmpReviewNote = null;
            } else {
              _tmpReviewNote = _cursor.getString(_cursorIndexOfReviewNote);
            }
            _item = new SongRequestEntity(_tmpId,_tmpTitleEn,_tmpTitleNative,_tmpLanguage,_tmpNotes,_tmpRequestedByName,_tmpStatus,_tmpCreatedAtEpochMillis,_tmpReviewedAtEpochMillis,_tmpReviewNote);
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
  public Object getById(final String id, final Continuation<? super SongRequestEntity> arg1) {
    final String _sql = "SELECT * FROM song_requests WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<SongRequestEntity>() {
      @Override
      @Nullable
      public SongRequestEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitleEn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleEn");
          final int _cursorIndexOfTitleNative = CursorUtil.getColumnIndexOrThrow(_cursor, "titleNative");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfRequestedByName = CursorUtil.getColumnIndexOrThrow(_cursor, "requestedByName");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtEpochMillis");
          final int _cursorIndexOfReviewedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewedAtEpochMillis");
          final int _cursorIndexOfReviewNote = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewNote");
          final SongRequestEntity _result;
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
            final String _tmpTitleNative;
            if (_cursor.isNull(_cursorIndexOfTitleNative)) {
              _tmpTitleNative = null;
            } else {
              _tmpTitleNative = _cursor.getString(_cursorIndexOfTitleNative);
            }
            final String _tmpLanguage;
            if (_cursor.isNull(_cursorIndexOfLanguage)) {
              _tmpLanguage = null;
            } else {
              _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpRequestedByName;
            if (_cursor.isNull(_cursorIndexOfRequestedByName)) {
              _tmpRequestedByName = null;
            } else {
              _tmpRequestedByName = _cursor.getString(_cursorIndexOfRequestedByName);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final long _tmpCreatedAtEpochMillis;
            _tmpCreatedAtEpochMillis = _cursor.getLong(_cursorIndexOfCreatedAtEpochMillis);
            final Long _tmpReviewedAtEpochMillis;
            if (_cursor.isNull(_cursorIndexOfReviewedAtEpochMillis)) {
              _tmpReviewedAtEpochMillis = null;
            } else {
              _tmpReviewedAtEpochMillis = _cursor.getLong(_cursorIndexOfReviewedAtEpochMillis);
            }
            final String _tmpReviewNote;
            if (_cursor.isNull(_cursorIndexOfReviewNote)) {
              _tmpReviewNote = null;
            } else {
              _tmpReviewNote = _cursor.getString(_cursorIndexOfReviewNote);
            }
            _result = new SongRequestEntity(_tmpId,_tmpTitleEn,_tmpTitleNative,_tmpLanguage,_tmpNotes,_tmpRequestedByName,_tmpStatus,_tmpCreatedAtEpochMillis,_tmpReviewedAtEpochMillis,_tmpReviewNote);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg1);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
