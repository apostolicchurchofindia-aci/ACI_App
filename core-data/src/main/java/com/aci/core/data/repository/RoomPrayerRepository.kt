package com.aci.core.data.repository

import com.aci.core.data.db.PrayerDao
import com.aci.core.domain.model.PrayerRequest
import com.aci.core.domain.model.Testimony
import com.aci.core.domain.repository.PrayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class RoomPrayerRepository(private val dao: PrayerDao) : PrayerRepository {

    override fun observeActiveRequests(): Flow<List<PrayerRequest>> =
        dao.observeActiveRequests().map { it.map { e -> e.toDomain() } }

    override fun observeAnsweredRequests(): Flow<List<PrayerRequest>> =
        dao.observeAnsweredRequests().map { it.map { e -> e.toDomain() } }

    override fun observeMyRequests(userId: String): Flow<List<PrayerRequest>> =
        dao.observeMyRequests(userId).map { it.map { e -> e.toDomain() } }

    override fun observePublicTestimonies(): Flow<List<Testimony>> =
        dao.observePublicTestimonies().map { it.map { e -> e.toDomain() } }

    override suspend fun submitRequest(request: PrayerRequest) {
        dao.insertPrayerRequest(request.toEntity())
    }

    override suspend fun prayFor(id: String) {
        dao.incrementPrayedCount(id)
    }

    override suspend fun markAnswered(id: String) {
        dao.markAnswered(id, Clock.System.now().toEpochMilliseconds())
    }

    override suspend fun submitTestimony(testimony: Testimony) {
        dao.insertTestimony(testimony.toEntity())
    }
}
