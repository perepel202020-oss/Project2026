package com.perepel.feature.settings.presentation.screen

import com.perepel.feature.settings.domain.model.Theme
import com.perepel.feature.settings.presentation.viewmodel.SettingsViewModel

class SettingsScreen(
    private val viewModel: SettingsViewModel
) {
    fun display() {
        println("\n" + "=".repeat(70))
        println("⚙️  НАСТРОЙКИ (ТЕМА)")
        println("=".repeat(70))

        val currentTheme = viewModel.currentTheme.value

        println("\n🎨 ВЫБЕРИТЕ ТЕМУ:")
        println("-".repeat(40))
        println("Текущая тема: ${getThemeDescription(currentTheme)}")
        println()
        println("1. ${if (currentTheme == Theme.DARK) "✅" else "⬜"} Тёмная тема")
        println("2. ${if (currentTheme == Theme.LIGHT) "✅" else "⬜"} Светлая тема")
        println("-".repeat(40))

        println("\n💡 КОМАНДЫ:")
        println("  toggle - переключить тему")
        println("  dark - установить тёмную тему")
        println("  light - установить светлую тему")
        println("  exit - выйти")
    }

    private fun getThemeDescription(theme: Theme): String {
        return when (theme) {
            Theme.DARK -> "🌙 Тёмная (экономит заряд батареи)"
            Theme.LIGHT -> "☀️ Светлая (лучшая читаемость днём)"
        }
    }

    fun handleCommand(command: String) {
        when (command) {
            "toggle" -> {
                viewModel.toggleTheme()
                println("✅ Тема переключена")
                display()
            }
            "dark" -> {
                viewModel.setTheme(Theme.DARK)
                println("✅ Установлена тёмная тема")
                display()
            }
            "light" -> {
                viewModel.setTheme(Theme.LIGHT)
                println("✅ Установлена светлая тема")
                display()
            }
            "exit" -> {
                println("👋 Выход из настроек")
            }
            else -> {
                println("❓ Неизвестная команда. Используйте: toggle, dark, light, exit")
            }
        }
    }
}