package com.perepel.feature.settings.presentation.viewmodel

import com.perepel.feature.settings.domain.model.Theme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsViewModel {
    private val _currentTheme = MutableStateFlow(Theme.DARK)
    val currentTheme: StateFlow<Theme> = _currentTheme.asStateFlow()

    fun toggleTheme() {
        _currentTheme.value = when (_currentTheme.value) {
            Theme.DARK -> Theme.LIGHT
            Theme.LIGHT -> Theme.DARK
        }
        println("🎨 Тема изменена на: ${_currentTheme.value}")
    }

    fun setTheme(theme: Theme) {
        _currentTheme.value = theme
    }
}