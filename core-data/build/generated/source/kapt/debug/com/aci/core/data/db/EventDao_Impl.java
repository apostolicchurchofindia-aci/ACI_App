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
import java.lang.Integer;
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
public final class EventDao_Impl implements EventDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<EventEntity> __insertionAdapterOfEventEntity;

  private final EntityInsertionAdapter<EventRegistrationEntity> __insertionAdapterOfEventRegistrationEntity;

  private final SharedSQLiteStatement __preparedStmtOfCheckIn;

  public EventDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEventEntity = new EntityInsertionAdapter<EventEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `events` (`id`,`title`,`category`,`description`,`imageUrl`,`startAtEpochMillis`,`endAtEpochMillis`,`locationName`,`address`,`branchId`,`registrationRequired`,`maxSeats`,`qrRequired`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final EventEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getCategory());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDescription());
        }
        if (entity.getImageUrl() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getImageUrl());
        }
        if (entity.getStartAtEpochMillis() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getStartAtEpochMillis());
        }
        if (entity.getEndAtEpochMillis() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getEndAtEpochMillis());
        }
        if (entity.getLocationName() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getLocationName());
        }
        if (entity.getAddress() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getAddress());
        }
        if (entity.getBranchId() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getBranchId());
        }
        final int _tmp = entity.getRegistrationRequired() ? 1 : 0;
        statement.bindLong(11, _tmp);
        statement.bindLong(12, entity.getMaxSeats());
        final int _tmp_1 = entity.getQrRequired() ? 1 : 0;
        statement.bindLong(13, _tmp_1);
      }
    };
    this.__insertionAdapterOfEventRegistrationEntity = new EntityInsertionAdapter<EventRegistrationEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `event_registrations` (`id`,`eventId`,`userId`,`name`,`phone`,`seats`,`registeredAtEpochMillis`,`checkedInAtEpochMillis`,`qrcodePayload`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final EventRegistrationEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getEventId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getEventId());
        }
        if (entity.getUserId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getUserId());
        }
        if (entity.getName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getName());
        }
        if (entity.getPhone() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getPhone());
        }
        statement.bindLong(6, entity.getSeats());
        statement.bindLong(7, entity.getRegisteredAtEpochMillis());
        if (entity.getCheckedInAtEpochMillis() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getCheckedInAtEpochMillis());
        }
        if (entity.getQrcodePayload() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getQrcodePayload());
        }
      }
    };
    this.__preparedStmtOfCheckIn = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE event_registrations SET checkedInAtEpochMillis = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertEvents(final List<EventEntity> events,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfEventEntity.insert(events);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object insertRegistration(final EventRegistrationEntity registration,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfEventRegistrationEntity.insert(registration);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object checkIn(final String id, final long checkedInAt,
      final Continuation<? super Unit> arg2) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfCheckIn.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, checkedInAt);
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
          __preparedStmtOfCheckIn.release(_stmt);
        }
      }
    }, arg2);
  }

  @Override
  public Flow<List<EventEntity>> observeEvents() {
    final String _sql = "SELECT * FROM events ORDER BY startAtEpochMillis ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"events"}, new Callable<List<EventEntity>>() {
      @Override
      @NonNull
      public List<EventEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfStartAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "startAtEpochMillis");
          final int _cursorIndexOfEndAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "endAtEpochMillis");
          final int _cursorIndexOfLocationName = CursorUtil.getColumnIndexOrThrow(_cursor, "locationName");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfBranchId = CursorUtil.getColumnIndexOrThrow(_cursor, "branchId");
          final int _cursorIndexOfRegistrationRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "registrationRequired");
          final int _cursorIndexOfMaxSeats = CursorUtil.getColumnIndexOrThrow(_cursor, "maxSeats");
          final int _cursorIndexOfQrRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "qrRequired");
          final List<EventEntity> _result = new ArrayList<EventEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EventEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final Long _tmpStartAtEpochMillis;
            if (_cursor.isNull(_cursorIndexOfStartAtEpochMillis)) {
              _tmpStartAtEpochMillis = null;
            } else {
              _tmpStartAtEpochMillis = _cursor.getLong(_cursorIndexOfStartAtEpochMillis);
            }
            final Long _tmpEndAtEpochMillis;
            if (_cursor.isNull(_cursorIndexOfEndAtEpochMillis)) {
              _tmpEndAtEpochMillis = null;
            } else {
              _tmpEndAtEpochMillis = _cursor.getLong(_cursorIndexOfEndAtEpochMillis);
            }
            final String _tmpLocationName;
            if (_cursor.isNull(_cursorIndexOfLocationName)) {
              _tmpLocationName = null;
            } else {
              _tmpLocationName = _cursor.getString(_cursorIndexOfLocationName);
            }
            final String _tmpAddress;
            if (_cursor.isNull(_cursorIndexOfAddress)) {
              _tmpAddress = null;
            } else {
              _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            }
            final String _tmpBranchId;
            if (_cursor.isNull(_cursorIndexOfBranchId)) {
              _tmpBranchId = null;
            } else {
              _tmpBranchId = _cursor.getString(_cursorIndexOfBranchId);
            }
            final boolean _tmpRegistrationRequired;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfRegistrationRequired);
            _tmpRegistrationRequired = _tmp != 0;
            final int _tmpMaxSeats;
            _tmpMaxSeats = _cursor.getInt(_cursorIndexOfMaxSeats);
            final boolean _tmpQrRequired;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfQrRequired);
            _tmpQrRequired = _tmp_1 != 0;
            _item = new EventEntity(_tmpId,_tmpTitle,_tmpCategory,_tmpDescription,_tmpImageUrl,_tmpStartAtEpochMillis,_tmpEndAtEpochMillis,_tmpLocationName,_tmpAddress,_tmpBranchId,_tmpRegistrationRequired,_tmpMaxSeats,_tmpQrRequired);
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
  public Flow<EventEntity> observeEvent(final String id) {
    final String _sql = "SELECT * FROM events WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"events"}, new Callable<EventEntity>() {
      @Override
      @Nullable
      public EventEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfStartAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "startAtEpochMillis");
          final int _cursorIndexOfEndAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "endAtEpochMillis");
          final int _cursorIndexOfLocationName = CursorUtil.getColumnIndexOrThrow(_cursor, "locationName");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfBranchId = CursorUtil.getColumnIndexOrThrow(_cursor, "branchId");
          final int _cursorIndexOfRegistrationRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "registrationRequired");
          final int _cursorIndexOfMaxSeats = CursorUtil.getColumnIndexOrThrow(_cursor, "maxSeats");
          final int _cursorIndexOfQrRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "qrRequired");
          final EventEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final Long _tmpStartAtEpochMillis;
            if (_cursor.isNull(_cursorIndexOfStartAtEpochMillis)) {
              _tmpStartAtEpochMillis = null;
            } else {
              _tmpStartAtEpochMillis = _cursor.getLong(_cursorIndexOfStartAtEpochMillis);
            }
            final Long _tmpEndAtEpochMillis;
            if (_cursor.isNull(_cursorIndexOfEndAtEpochMillis)) {
              _tmpEndAtEpochMillis = null;
            } else {
              _tmpEndAtEpochMillis = _cursor.getLong(_cursorIndexOfEndAtEpochMillis);
            }
            final String _tmpLocationName;
            if (_cursor.isNull(_cursorIndexOfLocationName)) {
              _tmpLocationName = null;
            } else {
              _tmpLocationName = _cursor.getString(_cursorIndexOfLocationName);
            }
            final String _tmpAddress;
            if (_cursor.isNull(_cursorIndexOfAddress)) {
              _tmpAddress = null;
            } else {
              _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            }
            final String _tmpBranchId;
            if (_cursor.isNull(_cursorIndexOfBranchId)) {
              _tmpBranchId = null;
            } else {
              _tmpBranchId = _cursor.getString(_cursorIndexOfBranchId);
            }
            final boolean _tmpRegistrationRequired;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfRegistrationRequired);
            _tmpRegistrationRequired = _tmp != 0;
            final int _tmpMaxSeats;
            _tmpMaxSeats = _cursor.getInt(_cursorIndexOfMaxSeats);
            final boolean _tmpQrRequired;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfQrRequired);
            _tmpQrRequired = _tmp_1 != 0;
            _result = new EventEntity(_tmpId,_tmpTitle,_tmpCategory,_tmpDescription,_tmpImageUrl,_tmpStartAtEpochMillis,_tmpEndAtEpochMillis,_tmpLocationName,_tmpAddress,_tmpBranchId,_tmpRegistrationRequired,_tmpMaxSeats,_tmpQrRequired);
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
  public Flow<List<EventRegistrationEntity>> observeRegistrationsForEvent(final String eventId) {
    final String _sql = "SELECT * FROM event_registrations WHERE eventId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (eventId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, eventId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"event_registrations"}, new Callable<List<EventRegistrationEntity>>() {
      @Override
      @NonNull
      public List<EventRegistrationEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEventId = CursorUtil.getColumnIndexOrThrow(_cursor, "eventId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfSeats = CursorUtil.getColumnIndexOrThrow(_cursor, "seats");
          final int _cursorIndexOfRegisteredAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "registeredAtEpochMillis");
          final int _cursorIndexOfCheckedInAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "checkedInAtEpochMillis");
          final int _cursorIndexOfQrcodePayload = CursorUtil.getColumnIndexOrThrow(_cursor, "qrcodePayload");
          final List<EventRegistrationEntity> _result = new ArrayList<EventRegistrationEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EventRegistrationEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpEventId;
            if (_cursor.isNull(_cursorIndexOfEventId)) {
              _tmpEventId = null;
            } else {
              _tmpEventId = _cursor.getString(_cursorIndexOfEventId);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final int _tmpSeats;
            _tmpSeats = _cursor.getInt(_cursorIndexOfSeats);
            final long _tmpRegisteredAtEpochMillis;
            _tmpRegisteredAtEpochMillis = _cursor.getLong(_cursorIndexOfRegisteredAtEpochMillis);
            final Long _tmpCheckedInAtEpochMillis;
            if (_cursor.isNull(_cursorIndexOfCheckedInAtEpochMillis)) {
              _tmpCheckedInAtEpochMillis = null;
            } else {
              _tmpCheckedInAtEpochMillis = _cursor.getLong(_cursorIndexOfCheckedInAtEpochMillis);
            }
            final String _tmpQrcodePayload;
            if (_cursor.isNull(_cursorIndexOfQrcodePayload)) {
              _tmpQrcodePayload = null;
            } else {
              _tmpQrcodePayload = _cursor.getString(_cursorIndexOfQrcodePayload);
            }
            _item = new EventRegistrationEntity(_tmpId,_tmpEventId,_tmpUserId,_tmpName,_tmpPhone,_tmpSeats,_tmpRegisteredAtEpochMillis,_tmpCheckedInAtEpochMillis,_tmpQrcodePayload);
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
  public Flow<List<EventRegistrationEntity>> observeMyRegistrations(final String userId) {
    final String _sql = "SELECT * FROM event_registrations WHERE userId = ? ORDER BY registeredAtEpochMillis DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"event_registrations"}, new Callable<List<EventRegistrationEntity>>() {
      @Override
      @NonNull
      public List<EventRegistrationEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEventId = CursorUtil.getColumnIndexOrThrow(_cursor, "eventId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfSeats = CursorUtil.getColumnIndexOrThrow(_cursor, "seats");
          final int _cursorIndexOfRegisteredAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "registeredAtEpochMillis");
          final int _cursorIndexOfCheckedInAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "checkedInAtEpochMillis");
          final int _cursorIndexOfQrcodePayload = CursorUtil.getColumnIndexOrThrow(_cursor, "qrcodePayload");
          final List<EventRegistrationEntity> _result = new ArrayList<EventRegistrationEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EventRegistrationEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpEventId;
            if (_cursor.isNull(_cursorIndexOfEventId)) {
              _tmpEventId = null;
            } else {
              _tmpEventId = _cursor.getString(_cursorIndexOfEventId);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final int _tmpSeats;
            _tmpSeats = _cursor.getInt(_cursorIndexOfSeats);
            final long _tmpRegisteredAtEpochMillis;
            _tmpRegisteredAtEpochMillis = _cursor.getLong(_cursorIndexOfRegisteredAtEpochMillis);
            final Long _tmpCheckedInAtEpochMillis;
            if (_cursor.isNull(_cursorIndexOfCheckedInAtEpochMillis)) {
              _tmpCheckedInAtEpochMillis = null;
            } else {
              _tmpCheckedInAtEpochMillis = _cursor.getLong(_cursorIndexOfCheckedInAtEpochMillis);
            }
            final String _tmpQrcodePayload;
            if (_cursor.isNull(_cursorIndexOfQrcodePayload)) {
              _tmpQrcodePayload = null;
            } else {
              _tmpQrcodePayload = _cursor.getString(_cursorIndexOfQrcodePayload);
            }
            _item = new EventRegistrationEntity(_tmpId,_tmpEventId,_tmpUserId,_tmpName,_tmpPhone,_tmpSeats,_tmpRegisteredAtEpochMillis,_tmpCheckedInAtEpochMillis,_tmpQrcodePayload);
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
  public Object countEvents(final Continuation<? super Integer> arg0) {
    final String _sql = "SELECT COUNT(*) FROM events";
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
