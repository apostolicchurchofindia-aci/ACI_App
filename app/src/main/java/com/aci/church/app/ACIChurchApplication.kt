package com.aci.church.app

import android.app.Application
import com.aci.core.data.di.ServiceLocator
import com.aci.data.content.BibleSeeder
import com.aci.data.content.EventSeeder
import com.aci.data.content.ChurchContent
import com.aci.data.content.SongSeeder
import com.aci.data.content.UserSeeder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber

class ACIChurchApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        ServiceLocator.init(this)
        ChurchContent.init(this)
        applicationScope.launch {
            SongSeeder.seedIfNeeded(ServiceLocator.songRepository, this@ACIChurchApplication)
        }
        applicationScope.launch {
            BibleSeeder.seedIfNeeded(ServiceLocator.bibleRepository, this@ACIChurchApplication)
        }
        applicationScope.launch {
            EventSeeder.seedIfNeeded(ServiceLocator.eventRepository, this@ACIChurchApplication)
        }
        applicationScope.launch {
            // Seed first, then restore — a saved session points at a row that has to exist.
            UserSeeder.seedIfNeeded(ServiceLocator.userRepository, this@ACIChurchApplication)
            ServiceLocator.userRepository.restoreSession()
        }
    }
}
