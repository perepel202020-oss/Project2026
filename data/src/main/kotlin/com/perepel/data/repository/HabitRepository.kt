package com.perepel.data.repository

import com.perepel.data.local.dao.HabitCompletionDao
import com.perepel.data.local.dao.HabitDao
import com.perepel.data.local.entity.HabitCompletionEntity
import com.perepel.data.local.entity.HabitEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class HabitRepository @Inject constructor(
    private val habitDao: HabitDao,
    private val completionDao: HabitCompletionDao
) {
    // Habit operations
    fun getAllHabits(): Flow<List<HabitEntity>> = habitDao.getAllHabits()

    suspend fun getHabitById(id: Long): HabitEntity? = habitDao.getHabitById(id)

    suspend fun insertHabit(habit: HabitEntity): Long = habitDao.insertHabit(habit)

    suspend fun updateHabit(habit: HabitEntity) = habitDao.updateHabit(habit)

    suspend fun deleteHabit(habit: HabitEntity) = habitDao.deleteHabit(habit)

    fun getActiveHabits(): Flow<List<HabitEntity>> = habitDao.getActiveHabits()

    // Completion operations
    suspend fun markHabitCompleted(habitId: Long, date: LocalDate = LocalDate.now(), notes: String? = null) {
        val completion = HabitCompletionEntity(
            habitId = habitId,
            date = date,
            notes = notes
        )
        completionDao.insertCompletion(completion)
    }

    suspend fun unmarkHabitCompleted(habitId: Long, date: LocalDate = LocalDate.now()) {
        completionDao.deleteCompletionByDate(habitId, date)
    }

    fun getCompletionsForHabit(habitId: Long): Flow<List<HabitCompletionEntity>> =
        completionDao.getCompletionsForHabit(habitId)

    // Statistics
    fun getActiveHabitsCount(): Flow<Int> = habitDao.getActiveHabitsCount()

    fun getTotalCompletionsCount(habitId: Long): Flow<Int> =
        completionDao.getTotalCompletionsCount(habitId)

    // Check completion status
    suspend fun isHabitCompletedToday(habitId: Long): Boolean {
        val today = LocalDate.now()
        return completionDao.getCompletion(habitId, today) != null
    }
}