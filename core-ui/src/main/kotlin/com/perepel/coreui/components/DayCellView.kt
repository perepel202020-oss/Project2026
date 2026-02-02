package com.perepel.coreui.components

import java.time.LocalDate

/**
 * Компонент ячейки дня для календаря
 */
data class DayCellView(
    val date: LocalDate,
    val isToday: Boolean,
    val completedCount: Int,
    val totalCount: Int,
    val hasNote: Boolean = false,
    val mood: Int? = null // 1-5: плохое настроение - хорошее
) {

    val progress: Float
        get() = if (totalCount > 0) completedCount.toFloat() / totalCount else 0f

    fun getDisplayText(): String {
        return date.dayOfMonth.toString()
    }

    fun getStatusColor(): String {
        return when {
            isToday -> "#FF5252" // Красный для сегодня
            completedCount == totalCount && totalCount > 0 -> "#4CAF50" // Зелёный если всё выполнено
            progress >= 0.5f -> "#FFC107" // Жёлтый если выполнено больше половины
            else -> "#9E9E9E" // Серый если меньше половины
        }
    }

    fun getMoodEmoji(): String? {
        return mood?.let {
            when (it) {
                1 -> "😢"
                2 -> "😔"
                3 -> "😐"
                4 -> "🙂"
                5 -> "😊"
                else -> null
            }
        }
    }
}