package com.aci.core.data.repository

import com.aci.core.data.db.ChapterReadEntity
import com.aci.core.data.db.ReadingPlanDao
import com.aci.core.data.db.ReadingPlanStateEntity
import com.aci.core.domain.model.ReadingPlanState
import com.aci.core.domain.model.chapterKey
import com.aci.core.domain.repository.ReadingPlanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

class RoomReadingPlanRepository(
    private val dao: ReadingPlanDao
) : ReadingPlanRepository {

    override fun observeState(userId: String): Flow<ReadingPlanState?> =
        combine(
            dao.observeState(userId),
            dao.observeChapterReads(userId)
        ) { state, reads ->
            state?.let {
                ReadingPlanState(
                    startDate = LocalDate.fromEpochDays(it.startDateEpochDay.toInt()),
                    readChapters = reads.associate { r ->
                        chapterKey(r.bookId, r.chapter) to Instant.fromEpochMilliseconds(r.readAtEpochMillis)
                    }
                )
            }
        }

    override suspend fun start(userId: String, startDate: LocalDate) {
        dao.upsertState(ReadingPlanStateEntity(userId, startDate.toEpochDays().toLong()))
    }

    override suspend fun markChapterRead(userId: String, bookId: String, chapter: Int) {
        dao.insertChapterRead(
            ChapterReadEntity(
                userId = userId,
                bookId = bookId,
                chapter = chapter,
                readAtEpochMillis = Clock.System.now().toEpochMilliseconds()
            )
        )
    }
}
