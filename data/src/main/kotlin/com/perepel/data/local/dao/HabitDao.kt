package com.perepel.data.local.dao

import androidx.room.*
import com.perepel.data.local.entity.HabitCompletionEntity
import com.perepel.data.local.entity.HabitEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime

@Dao
interface HabitDao {
    // CRUD для привычек
    @Query("SELECT * FROM habits ORDER BY createdAt DESC")
    fun getAllHabits(): Flow<List<HabitEntity>>

    @Query("SELECT * FROM habits WHERE id = :id")
    suspend fun getHabitById(id: Long): HabitEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: HabitEntity): Long

    @Update
    suspend fun updateHabit(habit: HabitEntity)

    @Query("UPDATE habits SET isActive = :isActive WHERE id = :id")
    suspend fun updateHabitStatus(id: Long, isActive: Boolean)

    @Delete
    suspend fun deleteHabit(habit: HabitEntity)

    @Query("DELETE FROM habits WHERE id = :id")
    suspend fun deleteHabitById(id: Long)

    @Query("SELECT * FROM habits WHERE isActive = 1")
    fun getActiveHabits(): Flow<List<HabitEntity>>

    @Query("SELECT * FROM habits WHERE category = :category")
    fun getHabitsByCategory(category: String): Flow<List<HabitEntity>>

    // Статистика
    @Query("SELECT COUNT(*) FROM habits WHERE isActive = 1")
    fun getActiveHabitsCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM habits")
    fun getTotalHabitsCount(): Flow<Int>

    // Поиск
    @Query("SELECT * FROM habits WHERE name LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun searchHabits(query: String): Flow<List<HabitEntity>>
}