package com.perepel.data.local.dao

import androidx.room.*
import com.perepel.data.local.entity.HabitCompletionEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime

@Dao
interface HabitCompletionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCompletion(completion: HabitCompletionEntity)

    @Query("SELECT * FROM habit_completions WHERE habitId = :habitId AND date = :date")
    suspend fun getCompletion(habitId: Long, date: LocalDate): HabitCompletionEntity?

    @Query("SELECT * FROM habit_completions WHERE habitId = :habitId")
    fun getCompletionsForHabit(habitId: Long): Flow<List<HabitCompletionEntity>>

    @Query("SELECT * FROM habit_completions WHERE habitId = :habitId AND date BETWEEN :startDate AND :endDate")
    fun getCompletionsForPeriod(
        habitId: Long,
        startDate: LocalDate,
        endDate: LocalDate
    ): Flow<List<HabitCompletionEntity>>

    @Query("SELECT COUNT(*) FROM habit_completions WHERE habitId = :habitId")
    fun getTotalCompletionsCount(habitId: Long): Flow<Int>

    @Query("SELECT COUNT(DISTINCT date) FROM habit_completions WHERE habitId = :habitId")
    fun getUniqueDaysCount(habitId: Long): Flow<Int>

    @Query("DELETE FROM habit_completions WHERE id = :id")
    suspend fun deleteCompletion(id: Long)

    @Query("DELETE FROM habit_completions WHERE habitId = :habitId AND date = :date")
    suspend fun deleteCompletionByDate(habitId: Long, date: LocalDate)

    // Получение последних записей для виджета/уведомлений
    @Query("SELECT * FROM habit_completions WHERE date >= :sinceDate ORDER BY completedAt DESC LIMIT :limit")
    fun getRecentCompletions(sinceDate: LocalDate, limit: Int): Flow<List<HabitCompletionEntity>>
}