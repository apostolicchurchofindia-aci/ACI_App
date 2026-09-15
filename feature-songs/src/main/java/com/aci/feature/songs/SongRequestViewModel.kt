package com.aci.feature.songs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aci.core.data.di.ServiceLocator
import com.aci.core.domain.model.SongRequest
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class SongRequestViewModel : ViewModel() {
    private val repository = ServiceLocator.songRequestRepository

    fun submit(titleEn: String, titleNative: String, language: String, notes: String) {
        if (titleEn.isBlank()) return
        viewModelScope.launch {
            repository.submit(
                SongRequest(
                    id = "song-request-${Clock.System.now().toEpochMilliseconds()}",
                    titleEn = titleEn,
                    titleNative = titleNative,
                    language = language,
                    notes = notes,
                    requestedByName = "",
                    createdAt = Clock.System.now()
                )
            )
        }
    }
}
