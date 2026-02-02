package com.perepel.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.perepel.data.local.converter.LocalDateTimeConverter
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(
    tableName = "habit_completions",
    foreignKeys = [
        ForeignKey(
            entity = HabitEntity::class,
            parentColumns = ["id"],
            childColumns = ["habitId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("habitId"), Index("date")]
)
@TypeConverters(LocalDateTimeConverter::class)
data class HabitCompletionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val habitId: Long,
    val date: LocalDate,
    val completedAt: LocalDateTime = LocalDateTime.now(),
    val notes: String? = null,
    val value: Int = 1 // for habits with target > 1
)