package com.perepel.feature.notifications.data

import com.perepel.feature.notifications.domain.Notification
import com.perepel.feature.notifications.domain.NotificationRepository
import kotlinx.coroutines.delay

class NotificationRepositoryImpl : NotificationRepository {

    private val notifications = mutableListOf(
        Notification(
            id = "1",
            title = "Доброе утро!",
            message = "Время проверить ваши правила на сегодня!",
            time = "08:00",
            daysOfWeek = listOf(1, 2, 3, 4, 5), // Пн-Пт
            enabled = true
        ),
        Notification(
            id = "2",
            title = "Обзор дня",
            message = "Как идут дела с выполнением правил?",
            time = "20:00",
            daysOfWeek = listOf(1, 2, 3, 4, 5, 6, 7), // Каждый день
            enabled = true
        ),
        Notification(
            id = "3",
            title = "Не забывайте!",
            message = "Осталось выполнить несколько правил",
            time = "22:00",
            daysOfWeek = listOf(1, 2, 3, 4, 5), // Пн-Пт
            enabled = false
        )
    )

    override suspend fun getScheduledNotifications(): List<Notification> {
        delay(100)
        return notifications.toList()
    }

    override suspend fun scheduleNotification(notification: Notification) {
        delay(100)
        val index = notifications.indexOfFirst { it.id == notification.id }
        if (index != -1) {
            notifications[index] = notification
        } else {
            notifications.add(notification)
        }
        println("📅 Уведомление запланировано: ${notification.title} в ${notification.time}")
    }

    override suspend fun cancelNotification(id: String) {
        delay(100)
        notifications.removeIf { it.id == id }
        println("❌ Уведомление отменено: $id")
    }

    override suspend fun toggleNotification(id: String, enabled: Boolean) {
        delay(100)
        val index = notifications.indexOfFirst { it.id == id }
        if (index != -1) {
            val notification = notifications[index]
            notifications[index] = notification.copy(enabled = enabled)
            val status = if (enabled) "включено" else "выключено"
            println("🔧 Уведомление $id $status")
        }
    }
}