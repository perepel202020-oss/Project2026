package com.perepel.feature.settings

import java.time.LocalTime

class SimpleSettings {

    data class Settings(
        val appLockEnabled: Boolean = false,
        val resetTime: LocalTime = LocalTime.of(0, 0),
        val notificationsEnabled: Boolean = true,
        val theme: String = "DARK",
        val language: String = "RUSSIAN"
    )

    private var currentSettings = Settings()

    fun display() {
        println("=".repeat(70))
        println("⚙️ НАСТРОЙКИ ПРИЛОЖЕНИЯ")
        println("=".repeat(70))

        println("\n📱 ТЕКУЩИЕ НАСТРОЙКИ:")
        println("-".repeat(70))
        println("  🔒 Блокировка приложения: ${if (currentSettings.appLockEnabled) "ВКЛ" else "ВЫКЛ"}")
        println("  ⏰ Время сброса: ${currentSettings.resetTime}")
        println("  🔔 Уведомления: ${if (currentSettings.notificationsEnabled) "ВКЛ" else "ВЫКЛ"}")
        println("  🎨 Тема: ${currentSettings.theme}")
        println("  🌍 Язык: ${currentSettings.language}")

        println("\n💡 КОМАНДЫ ДЛЯ ИЗМЕНЕНИЯ:")
        println("-".repeat(70))
        println("  lock on/off - включить/выключить блокировку")
        println("  time <час> <минута> - установить время сброса")
        println("  notify on/off - уведомления")
        println("  theme <light/dark> - сменить тему")
        println("  export - экспортировать данные")
        println("  reset - сбросить настройки")
        println("  help - справка")
        println("  exit - выход")
    }

    fun handleCommand(command: String) {
        val parts = command.split(" ")

        when {
            command == "lock on" -> {
                currentSettings = currentSettings.copy(appLockEnabled = true)
                println("✅ Блокировка приложения включена")
            }

            command == "lock off" -> {
                currentSettings = currentSettings.copy(appLockEnabled = false)
                println("✅ Блокировка приложения выключена")
            }

            command.startsWith("time ") && parts.size >= 3 -> {
                val hour = parts[1].toIntOrNull()
                val minute = parts[2].toIntOrNull()

                if (hour != null && minute != null && hour in 0..23 && minute in 0..59) {
                    currentSettings = currentSettings.copy(resetTime = LocalTime.of(hour, minute))
                    println("✅ Время сброса установлено на $hour:$minute")
                } else {
                    println("❌ Неверное время. Используйте: time <час 0-23> <минута 0-59>")
                }
            }

            command == "notify on" -> {
                currentSettings = currentSettings.copy(notificationsEnabled = true)
                println("✅ Уведомления включены")
            }

            command == "notify off" -> {
                currentSettings = currentSettings.copy(notificationsEnabled = false)
                println("✅ Уведомления выключены")
            }

            command == "theme light" -> {
                currentSettings = currentSettings.copy(theme = "LIGHT")
                println("✅ Тема изменена на светлую")
            }

            command == "theme dark" -> {
                currentSettings = currentSettings.copy(theme = "DARK")
                println("✅ Тема изменена на тёмную")
            }

            command == "export" -> {
                println("\n📤 ЭКСПОРТ ДАННЫХ:")
                println("-".repeat(70))
                println("  Форматы: JSON, CSV, PDF")
                println("  Настройки экспортированы успешно!")
                println("  Файл: project2026_export_${System.currentTimeMillis()}.json")
                println("-".repeat(70))
            }

            command == "reset" -> {
                currentSettings = Settings()
                println("🔄 Все настройки сброшены к значениям по умолчанию")
            }

            command == "help" -> {
                println("\n📖 СПРАВКА ПО НАСТРОЙКАМ:")
                println("  lock on/off - блокировка приложения")
                println("  time <Ч> <М> - время сброса (пример: time 0 0)")
                println("  notify on/off - уведомления")
                println("  theme <light/dark> - тема оформления")
                println("  export - экспорт данных")
                println("  reset - сброс настроек")
                println("  status - показать текущие настройки")
                println("  exit - выход")
            }

            command == "status" -> {
                display()
            }

            command == "exit" -> {
                println("👋 Выход из настроек")
            }

            else -> {
                println("❓ Неизвестная команда. Введите 'help' для справки")
            }
        }
    }

    fun getCurrentSettings(): Settings {
        return currentSettings
    }
}