package com.perepel.feature.notifications.domain.model

import java.time.LocalTime

data class Notification(
    val id: String,
    val title: String,
    val message: String,
    val time: LocalTime,
    val isEnabled: Boolean = true,
    val daysOfWeek: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7), // 1=Пн, 7=Вс
    val notificationType: NotificationType = NotificationType.MOTIVATIONAL
) {
    fun shouldTrigger(dayOfWeek: Int, currentTime: LocalTime): Boolean {
        return isEnabled &&
                daysOfWeek.contains(dayOfWeek) &&
                currentTime.hour == time.hour &&
                currentTime.minute == time.minute
    }
}

enum class NotificationType {
    MORNING_REMINDER,    // Утреннее напоминание
    DAY_PROGRESS,        // Прогресс за день
    EVENING_REVIEW,      // Вечерний обзор
    MOTIVATIONAL,        // Мотивационное
    ACHIEVEMENT          // Достижение
}