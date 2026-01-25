package com.perepel.feature.notifications.domain

import kotlinx.coroutines.runBlocking

class NotificationScheduler(
    private val repository: NotificationRepository,
    private val onNotification: (Notification) -> Unit
) {
    fun simulateNotification(dayOfWeek: Int, hour: Int, minute: Int) {
        println("⏰ Симуляция уведомления: день $dayOfWeek, время $hour:$minute")

        // Ищем подходящее уведомление
        val notifications = runBlocking { repository.getScheduledNotifications() }
        val notification = notifications.find {
            it.enabled &&
                    it.daysOfWeek.contains(dayOfWeek) &&
                    it.time == "${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}"
        }

        if (notification != null) {
            onNotification(notification)
        } else {
            println("🔕 Нет активных уведомлений на это время")
        }
    }
}
