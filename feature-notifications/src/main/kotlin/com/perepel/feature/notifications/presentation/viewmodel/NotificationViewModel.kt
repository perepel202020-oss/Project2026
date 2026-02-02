package com.perepel.feature.notifications.presentation.viewmodel

import com.perepel.feature.notifications.domain.Notification
import com.perepel.feature.notifications.domain.NotificationRepository
import com.perepel.feature.notifications.domain.NotificationScheduler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotificationViewModel(
    private val repository: NotificationRepository,
    private val scheduler: NotificationScheduler
) {
    private val _notifications = MutableStateFlow<List<Notification>>(emptyList())
    val notifications: StateFlow<List<Notification>> = _notifications.asStateFlow()

    private val _uiState = MutableStateFlow<NotificationUiState>(NotificationUiState.Loading)
    val uiState: StateFlow<NotificationUiState> = _uiState.asStateFlow()

    suspend fun loadNotifications() {
        _uiState.value = NotificationUiState.Loading
        try {
            val notifications = repository.getScheduledNotifications()
            _notifications.value = notifications
            _uiState.value = NotificationUiState.Success(notifications)
        } catch (e: Exception) {
            _uiState.value = NotificationUiState.Error(e.message ?: "Unknown error")
        }
    }

    suspend fun toggleNotification(id: String) {
        val notification = _notifications.value.find { it.id == id }
        if (notification != null) {
            val newEnabledState = !notification.enabled
            repository.toggleNotification(id, newEnabledState)
            loadNotifications() // Обновляем список
        }
    }

    fun simulateNotification(dayOfWeek: Int, hour: Int, minute: Int) {
        scheduler.simulateNotification(dayOfWeek, hour, minute)
    }
}

sealed class NotificationUiState {
    object Loading : NotificationUiState()
    data class Success(val notifications: List<Notification>) : NotificationUiState()
    data class Error(val message: String) : NotificationUiState()
}