package com.perepel.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.perepel.data.local.converter.LocalDateTimeConverter
import com.perepel.data.local.converter.ListConverter
import java.time.LocalDateTime

@Entity(tableName = "habits")
@TypeConverters(LocalDateTimeConverter::class, ListConverter::class)
data class HabitEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String? = null,
    val icon: String? = null,
    val color: String = "#4CAF50",
    val targetCount: Int = 1,
    val period: String = "daily", // daily, weekly, monthly
    val category: String? = null,
    val tags: List<String> = emptyList(),
    val isActive: Boolean = true,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val reminderTime: String? = null, // "HH:mm" format
    val streak: Int = 0,
    val bestStreak: Int = 0
)