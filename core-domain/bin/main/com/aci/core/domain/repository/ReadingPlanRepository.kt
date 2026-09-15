package com.aci.core.domain.repository

import com.aci.core.domain.model.ReadingPlanState
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface ReadingPlanRepository {
    /** Emits null until the member starts the plan. */
    fun observeState(userId: String): Flow<ReadingPlanState?>

    suspend fun start(userId: String, startDate: LocalDate)

    /** The only way a day completes: every chapter it covers has to be read. */
    suspend fun markChapterRead(userId: String, bookId: String, chapter: Int)
}
