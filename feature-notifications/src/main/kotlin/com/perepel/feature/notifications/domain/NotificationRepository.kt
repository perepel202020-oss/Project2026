package com.perepel.feature.notifications.domain

interface NotificationRepository {
    suspend fun getScheduledNotifications(): List<Notification>
    suspend fun scheduleNotification(notification: Notification)
    suspend fun cancelNotification(id: String)
    suspend fun toggleNotification(id: String, enabled: Boolean)
}

data class Notification(
    val id: String,
    val title: String,
    val message: String,
    val time: String, // "HH:mm"
    val daysOfWeek: List<Int>, // 1-7 (понедельник-воскресенье)
    val enabled: Boolean = true
)