package com.aci.core.data.di

import android.content.Context
import androidx.room.Room
import com.aci.core.data.auth.SessionStore
import com.aci.core.data.db.AciDatabase
import com.aci.core.data.repository.RoomBibleRepository
import com.aci.core.data.repository.RoomEventRepository
import com.aci.core.data.repository.RoomPrayerRepository
import com.aci.core.data.repository.RoomReadingPlanRepository
import com.aci.core.data.repository.RoomSongRepository
import com.aci.core.data.repository.RoomSongRequestRepository
import com.aci.core.data.repository.RoomUserRepository
import com.aci.core.domain.repository.BibleRepository
import com.aci.core.domain.repository.EventRepository
import com.aci.core.domain.repository.PrayerRepository
import com.aci.core.domain.repository.ReadingPlanRepository
import com.aci.core.domain.repository.SongRepository
import com.aci.core.domain.repository.SongRequestRepository

/**
 * Manual dependency container (no DI framework yet — matches the module-boundary plan
 * documented for a future Hilt upgrade). Call [init] once from Application.onCreate.
 */
object ServiceLocator {
    private var database: AciDatabase? = null

    lateinit var songRepository: SongRepository
        private set
    lateinit var bibleRepository: BibleRepository
        private set
    lateinit var prayerRepository: PrayerRepository
        private set
    lateinit var eventRepository: EventRepository
        private set
    lateinit var songRequestRepository: SongRequestRepository
        private set
    lateinit var userRepository: RoomUserRepository
        private set
    lateinit var readingPlanRepository: ReadingPlanRepository
        private set

    fun init(context: Context) {
        if (database != null) return
        val db = Room.databaseBuilder(
            context.applicationContext,
            AciDatabase::class.java,
            AciDatabase.DATABASE_NAME
        )
            // Local-only demo app, no server to reconcile against — destructive migration
            // is an acceptable tradeoff over hand-writing Migration objects at this stage.
            .fallbackToDestructiveMigration()
            .build()
        database = db
        songRepository = RoomSongRepository(db.songDao())
        bibleRepository = RoomBibleRepository(db.bibleDao())
        prayerRepository = RoomPrayerRepository(db.prayerDao())
        eventRepository = RoomEventRepository(db.eventDao())
        songRequestRepository = RoomSongRequestRepository(db.songRequestDao(), db.songDao())
        userRepository = RoomUserRepository(db.userDao(), SessionStore(context.applicationContext))
        readingPlanRepository = RoomReadingPlanRepository(db.readingPlanDao())
    }
}
