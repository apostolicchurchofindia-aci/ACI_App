package com.aci.core.domain.repository

import com.aci.core.domain.model.PrayerRequest
import com.aci.core.domain.model.Testimony
import kotlinx.coroutines.flow.Flow

interface PrayerRepository {
    fun observeActiveRequests(): Flow<List<PrayerRequest>>
    fun observeAnsweredRequests(): Flow<List<PrayerRequest>>
    fun observeMyRequests(userId: String): Flow<List<PrayerRequest>>
    fun observePublicTestimonies(): Flow<List<Testimony>>

    suspend fun submitRequest(request: PrayerRequest)
    suspend fun prayFor(id: String)
    suspend fun markAnswered(id: String)
    suspend fun submitTestimony(testimony: Testimony)
}
