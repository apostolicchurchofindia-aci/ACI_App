package com.aci.core.domain.repository

import com.aci.core.domain.model.SongRequest
import kotlinx.coroutines.flow.Flow

interface SongRequestRepository {
    fun observePending(): Flow<List<SongRequest>>
    fun observeAll(): Flow<List<SongRequest>>

    suspend fun submit(request: SongRequest)

    /** Approves the request and creates the corresponding catalog [com.aci.core.domain.model.Song]. */
    suspend fun approve(id: String, reviewNote: String = "")
    suspend fun reject(id: String, reviewNote: String = "")
}
