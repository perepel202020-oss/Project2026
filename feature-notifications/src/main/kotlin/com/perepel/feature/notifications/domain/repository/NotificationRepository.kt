package com.perepel.feature.notifications.domain.repository

import com.perepel.feature.notifications.domain.model.Notification
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    suspend fun getAllNotifications(): List<Notification>
    suspend fun getNotificationById(id: String): Notification?
    suspend fun saveNotification(notification: Notification)
    suspend fun deleteNotification(id: String)
    suspend fun toggleNotification(id: String, isEnabled: Boolean)
    fun observeNotifications(): Flow<List<Notification>>
    suspend fun getNotificationsForTime(hour: Int, minute: Int): List<Notification>
}