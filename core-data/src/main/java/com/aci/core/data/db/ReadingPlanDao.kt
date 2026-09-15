package com.aci.core.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ReadingPlanDao {
    @Query("SELECT * FROM reading_plan_state WHERE userId = :userId LIMIT 1")
    fun observeState(userId: String): Flow<ReadingPlanStateEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertState(state: ReadingPlanStateEntity)

    @Query("SELECT * FROM chapter_reads WHERE userId = :userId")
    fun observeChapterReads(userId: String): Flow<List<ChapterReadEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertChapterRead(read: ChapterReadEntity)
}
