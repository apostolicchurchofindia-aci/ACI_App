package com.aci.feature.songs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aci.core.data.di.ServiceLocator
import com.aci.core.domain.model.Song
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

/** Reads the Room-backed song catalogue via [ServiceLocator]. No DI framework yet. */
class SongsViewModel : ViewModel() {
    private val repository = ServiceLocator.songRepository

    val songs: StateFlow<List<Song>> = repository.observeSongs()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
}
