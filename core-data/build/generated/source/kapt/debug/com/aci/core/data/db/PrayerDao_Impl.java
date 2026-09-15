package com.aci.core.data.db;

import android.database.Cursor;
import androidx.annotation.NonNull;
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
public final class PrayerDao_Impl implements PrayerDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PrayerRequestEntity> __insertionAdapterOfPrayerRequestEntity;

  private final EntityInsertionAdapter<TestimonyEntity> __insertionAdapterOfTestimonyEntity;

  private final SharedSQLiteStatement __preparedStmtOfIncrementPrayedCount;

  private final SharedSQLiteStatement __preparedStmtOfMarkAnswered;

  public PrayerDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPrayerRequestEntity = new EntityInsertionAdapter<PrayerRequestEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `prayer_requests` (`id`,`userId`,`branchId`,`text`,`isAnonymous`,`isPrivate`,`prayedCount`,`answered`,`answeredAtEpochMillis`,`createdAtEpochMillis`,`status`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PrayerRequestEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getUserId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getUserId());
        }
        if (entity.getBranchId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getBranchId());
        }
        if (entity.getText() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getText());
        }
        final int _tmp = entity.isAnonymous() ? 1 : 0;
        statement.bindLong(5, _tmp);
        final int _tmp_1 = entity.isPrivate() ? 1 : 0;
        statement.bindLong(6, _tmp_1);
        statement.bindLong(7, entity.getPrayedCount());
        final int _tmp_2 = entity.getAnswered() ? 1 : 0;
        statement.bindLong(8, _tmp_2);
        if (entity.getAnsweredAtEpochMillis() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getAnsweredAtEpochMillis());
        }
        statement.bindLong(10, entity.getCreatedAtEpochMillis());
        if (entity.getStatus() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getStatus());
        }
      }
    };
    this.__insertionAdapterOfTestimonyEntity = new EntityInsertionAdapter<TestimonyEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `testimonies` (`id`,`userId`,`branchId`,`title`,`description`,`mediaUrlsJson`,`isPublic`,`approved`,`createdAtEpochMillis`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TestimonyEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getUserId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getUserId());
        }
        if (entity.getBranchId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getBranchId());
        }
        if (entity.getTitle() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDescription());
        }
        if (entity.getMediaUrlsJson() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getMediaUrlsJson());
        }
        final int _tmp = entity.isPublic() ? 1 : 0;
        statement.bindLong(7, _tmp);
        final int _tmp_1 = entity.getApproved() ? 1 : 0;
        statement.bindLong(8, _tmp_1);
        statement.bindLong(9, entity.getCreatedAtEpochMillis());
      }
    };
    this.__preparedStmtOfIncrementPrayedCount = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE prayer_requests SET prayedCount = prayedCount + 1 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfMarkAnswered = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE prayer_requests SET answered = 1, answeredAtEpochMillis = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertPrayerRequest(final PrayerRequestEntity request,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPrayerRequestEntity.insert(request);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object insertTestimony(final TestimonyEntity testimony,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTestimonyEntity.insert(testimony);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object incrementPrayedCount(final String id, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfIncrementPrayedCount.acquire();
        int _argIndex = 1;
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
          __preparedStmtOfIncrementPrayedCount.release(_stmt);
        }
      }
    }, arg1);
  }

  @Override
  public Object markAnswered(final String id, final long answeredAt,
      final Continuation<? super Unit> arg2) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkAnswered.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, answeredAt);
        _argIndex = 2;
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
          __preparedStmtOfMarkAnswered.release(_stmt);
        }
      }
    }, arg2);
  }

  @Override
  public Flow<List<PrayerRequestEntity>> observeActiveRequests() {
    final String _sql = "SELECT * FROM prayer_requests WHERE isPrivate = 0 AND answered = 0 ORDER BY createdAtEpochMillis DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"prayer_requests"}, new Callable<List<PrayerRequestEntity>>() {
      @Override
      @NonNull
      public List<PrayerRequestEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfBranchId = CursorUtil.getColumnIndexOrThrow(_cursor, "branchId");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfIsAnonymous = CursorUtil.getColumnIndexOrThrow(_cursor, "isAnonymous");
          final int _cursorIndexOfIsPrivate = CursorUtil.getColumnIndexOrThrow(_cursor, "isPrivate");
          final int _cursorIndexOfPrayedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "prayedCount");
          final int _cursorIndexOfAnswered = CursorUtil.getColumnIndexOrThrow(_cursor, "answered");
          final int _cursorIndexOfAnsweredAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "answeredAtEpochMillis");
          final int _cursorIndexOfCreatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtEpochMillis");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<PrayerRequestEntity> _result = new ArrayList<PrayerRequestEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PrayerRequestEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpBranchId;
            if (_cursor.isNull(_cursorIndexOfBranchId)) {
              _tmpBranchId = null;
            } else {
              _tmpBranchId = _cursor.getString(_cursorIndexOfBranchId);
            }
            final String _tmpText;
            if (_cursor.isNull(_cursorIndexOfText)) {
              _tmpText = null;
            } else {
              _tmpText = _cursor.getString(_cursorIndexOfText);
            }
            final boolean _tmpIsAnonymous;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsAnonymous);
            _tmpIsAnonymous = _tmp != 0;
            final boolean _tmpIsPrivate;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsPrivate);
            _tmpIsPrivate = _tmp_1 != 0;
            final int _tmpPrayedCount;
            _tmpPrayedCount = _cursor.getInt(_cursorIndexOfPrayedCount);
            final boolean _tmpAnswered;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfAnswered);
            _tmpAnswered = _tmp_2 != 0;
            final Long _tmpAnsweredAtEpochMillis;
            if (_cursor.isNull(_cursorIndexOfAnsweredAtEpochMillis)) {
              _tmpAnsweredAtEpochMillis = null;
            } else {
              _tmpAnsweredAtEpochMillis = _cursor.getLong(_cursorIndexOfAnsweredAtEpochMillis);
            }
            final long _tmpCreatedAtEpochMillis;
            _tmpCreatedAtEpochMillis = _cursor.getLong(_cursorIndexOfCreatedAtEpochMillis);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            _item = new PrayerRequestEntity(_tmpId,_tmpUserId,_tmpBranchId,_tmpText,_tmpIsAnonymous,_tmpIsPrivate,_tmpPrayedCount,_tmpAnswered,_tmpAnsweredAtEpochMillis,_tmpCreatedAtEpochMillis,_tmpStatus);
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
  public Flow<List<PrayerRequestEntity>> observeAnsweredRequests() {
    final String _sql = "SELECT * FROM prayer_requests WHERE isPrivate = 0 AND answered = 1 ORDER BY answeredAtEpochMillis DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"prayer_requests"}, new Callable<List<PrayerRequestEntity>>() {
      @Override
      @NonNull
      public List<PrayerRequestEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfBranchId = CursorUtil.getColumnIndexOrThrow(_cursor, "branchId");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfIsAnonymous = CursorUtil.getColumnIndexOrThrow(_cursor, "isAnonymous");
          final int _cursorIndexOfIsPrivate = CursorUtil.getColumnIndexOrThrow(_cursor, "isPrivate");
          final int _cursorIndexOfPrayedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "prayedCount");
          final int _cursorIndexOfAnswered = CursorUtil.getColumnIndexOrThrow(_cursor, "answered");
          final int _cursorIndexOfAnsweredAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "answeredAtEpochMillis");
          final int _cursorIndexOfCreatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtEpochMillis");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<PrayerRequestEntity> _result = new ArrayList<PrayerRequestEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PrayerRequestEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpBranchId;
            if (_cursor.isNull(_cursorIndexOfBranchId)) {
              _tmpBranchId = null;
            } else {
              _tmpBranchId = _cursor.getString(_cursorIndexOfBranchId);
            }
            final String _tmpText;
            if (_cursor.isNull(_cursorIndexOfText)) {
              _tmpText = null;
            } else {
              _tmpText = _cursor.getString(_cursorIndexOfText);
            }
            final boolean _tmpIsAnonymous;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsAnonymous);
            _tmpIsAnonymous = _tmp != 0;
            final boolean _tmpIsPrivate;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsPrivate);
            _tmpIsPrivate = _tmp_1 != 0;
            final int _tmpPrayedCount;
            _tmpPrayedCount = _cursor.getInt(_cursorIndexOfPrayedCount);
            final boolean _tmpAnswered;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfAnswered);
            _tmpAnswered = _tmp_2 != 0;
            final Long _tmpAnsweredAtEpochMillis;
            if (_cursor.isNull(_cursorIndexOfAnsweredAtEpochMillis)) {
              _tmpAnsweredAtEpochMillis = null;
            } else {
              _tmpAnsweredAtEpochMillis = _cursor.getLong(_cursorIndexOfAnsweredAtEpochMillis);
            }
            final long _tmpCreatedAtEpochMillis;
            _tmpCreatedAtEpochMillis = _cursor.getLong(_cursorIndexOfCreatedAtEpochMillis);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            _item = new PrayerRequestEntity(_tmpId,_tmpUserId,_tmpBranchId,_tmpText,_tmpIsAnonymous,_tmpIsPrivate,_tmpPrayedCount,_tmpAnswered,_tmpAnsweredAtEpochMillis,_tmpCreatedAtEpochMillis,_tmpStatus);
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
  public Flow<List<PrayerRequestEntity>> observeMyRequests(final String userId) {
    final String _sql = "SELECT * FROM prayer_requests WHERE userId = ? ORDER BY createdAtEpochMillis DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"prayer_requests"}, new Callable<List<PrayerRequestEntity>>() {
      @Override
      @NonNull
      public List<PrayerRequestEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfBranchId = CursorUtil.getColumnIndexOrThrow(_cursor, "branchId");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfIsAnonymous = CursorUtil.getColumnIndexOrThrow(_cursor, "isAnonymous");
          final int _cursorIndexOfIsPrivate = CursorUtil.getColumnIndexOrThrow(_cursor, "isPrivate");
          final int _cursorIndexOfPrayedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "prayedCount");
          final int _cursorIndexOfAnswered = CursorUtil.getColumnIndexOrThrow(_cursor, "answered");
          final int _cursorIndexOfAnsweredAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "answeredAtEpochMillis");
          final int _cursorIndexOfCreatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtEpochMillis");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<PrayerRequestEntity> _result = new ArrayList<PrayerRequestEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PrayerRequestEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpBranchId;
            if (_cursor.isNull(_cursorIndexOfBranchId)) {
              _tmpBranchId = null;
            } else {
              _tmpBranchId = _cursor.getString(_cursorIndexOfBranchId);
            }
            final String _tmpText;
            if (_cursor.isNull(_cursorIndexOfText)) {
              _tmpText = null;
            } else {
              _tmpText = _cursor.getString(_cursorIndexOfText);
            }
            final boolean _tmpIsAnonymous;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsAnonymous);
            _tmpIsAnonymous = _tmp != 0;
            final boolean _tmpIsPrivate;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsPrivate);
            _tmpIsPrivate = _tmp_1 != 0;
            final int _tmpPrayedCount;
            _tmpPrayedCount = _cursor.getInt(_cursorIndexOfPrayedCount);
            final boolean _tmpAnswered;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfAnswered);
            _tmpAnswered = _tmp_2 != 0;
            final Long _tmpAnsweredAtEpochMillis;
            if (_cursor.isNull(_cursorIndexOfAnsweredAtEpochMillis)) {
              _tmpAnsweredAtEpochMillis = null;
            } else {
              _tmpAnsweredAtEpochMillis = _cursor.getLong(_cursorIndexOfAnsweredAtEpochMillis);
            }
            final long _tmpCreatedAtEpochMillis;
            _tmpCreatedAtEpochMillis = _cursor.getLong(_cursorIndexOfCreatedAtEpochMillis);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            _item = new PrayerRequestEntity(_tmpId,_tmpUserId,_tmpBranchId,_tmpText,_tmpIsAnonymous,_tmpIsPrivate,_tmpPrayedCount,_tmpAnswered,_tmpAnsweredAtEpochMillis,_tmpCreatedAtEpochMillis,_tmpStatus);
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
  public Flow<List<TestimonyEntity>> observePublicTestimonies() {
    final String _sql = "SELECT * FROM testimonies WHERE isPublic = 1 AND approved = 1 ORDER BY createdAtEpochMillis DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"testimonies"}, new Callable<List<TestimonyEntity>>() {
      @Override
      @NonNull
      public List<TestimonyEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfBranchId = CursorUtil.getColumnIndexOrThrow(_cursor, "branchId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfMediaUrlsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "mediaUrlsJson");
          final int _cursorIndexOfIsPublic = CursorUtil.getColumnIndexOrThrow(_cursor, "isPublic");
          final int _cursorIndexOfApproved = CursorUtil.getColumnIndexOrThrow(_cursor, "approved");
          final int _cursorIndexOfCreatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtEpochMillis");
          final List<TestimonyEntity> _result = new ArrayList<TestimonyEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TestimonyEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpBranchId;
            if (_cursor.isNull(_cursorIndexOfBranchId)) {
              _tmpBranchId = null;
            } else {
              _tmpBranchId = _cursor.getString(_cursorIndexOfBranchId);
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpMediaUrlsJson;
            if (_cursor.isNull(_cursorIndexOfMediaUrlsJson)) {
              _tmpMediaUrlsJson = null;
            } else {
              _tmpMediaUrlsJson = _cursor.getString(_cursorIndexOfMediaUrlsJson);
            }
            final boolean _tmpIsPublic;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPublic);
            _tmpIsPublic = _tmp != 0;
            final boolean _tmpApproved;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfApproved);
            _tmpApproved = _tmp_1 != 0;
            final long _tmpCreatedAtEpochMillis;
            _tmpCreatedAtEpochMillis = _cursor.getLong(_cursorIndexOfCreatedAtEpochMillis);
            _item = new TestimonyEntity(_tmpId,_tmpUserId,_tmpBranchId,_tmpTitle,_tmpDescription,_tmpMediaUrlsJson,_tmpIsPublic,_tmpApproved,_tmpCreatedAtEpochMillis);
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
  public Flow<List<TestimonyEntity>> observePendingOrPrivateTestimonies() {
    final String _sql = "SELECT * FROM testimonies WHERE isPublic = 0 OR approved = 0 ORDER BY createdAtEpochMillis DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"testimonies"}, new Callable<List<TestimonyEntity>>() {
      @Override
      @NonNull
      public List<TestimonyEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfBranchId = CursorUtil.getColumnIndexOrThrow(_cursor, "branchId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfMediaUrlsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "mediaUrlsJson");
          final int _cursorIndexOfIsPublic = CursorUtil.getColumnIndexOrThrow(_cursor, "isPublic");
          final int _cursorIndexOfApproved = CursorUtil.getColumnIndexOrThrow(_cursor, "approved");
          final int _cursorIndexOfCreatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtEpochMillis");
          final List<TestimonyEntity> _result = new ArrayList<TestimonyEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TestimonyEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpBranchId;
            if (_cursor.isNull(_cursorIndexOfBranchId)) {
              _tmpBranchId = null;
            } else {
              _tmpBranchId = _cursor.getString(_cursorIndexOfBranchId);
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpMediaUrlsJson;
            if (_cursor.isNull(_cursorIndexOfMediaUrlsJson)) {
              _tmpMediaUrlsJson = null;
            } else {
              _tmpMediaUrlsJson = _cursor.getString(_cursorIndexOfMediaUrlsJson);
            }
            final boolean _tmpIsPublic;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPublic);
            _tmpIsPublic = _tmp != 0;
            final boolean _tmpApproved;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfApproved);
            _tmpApproved = _tmp_1 != 0;
            final long _tmpCreatedAtEpochMillis;
            _tmpCreatedAtEpochMillis = _cursor.getLong(_cursorIndexOfCreatedAtEpochMillis);
            _item = new TestimonyEntity(_tmpId,_tmpUserId,_tmpBranchId,_tmpTitle,_tmpDescription,_tmpMediaUrlsJson,_tmpIsPublic,_tmpApproved,_tmpCreatedAtEpochMillis);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
