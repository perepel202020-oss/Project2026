package com.perepel.feature.notifications.presentation.screen

import com.perepel.feature.notifications.presentation.viewmodel.NotificationViewModel
import kotlinx.coroutines.runBlocking

class NotificationScreen(
    private val viewModel: NotificationViewModel
) {
    fun display() {
        println("\n" + "=".repeat(70))
        println("🔔 НАСТРОЙКА УВЕДОМЛЕНИЙ")
        println("=".repeat(70))

        runBlocking {
            viewModel.loadNotifications()

            val notifications = viewModel.notifications.value
            if (notifications.isEmpty()) {
                println("📭 Уведомления не настроены")
            } else {
                notifications.forEach { notification ->
                    val status = if (notification.enabled) "✅" else "❌"
                    val days = notification.daysOfWeek.joinToString(", ") {
                        when (it) {
                            1 -> "Пн"
                            2 -> "Вт"
                            3 -> "Ср"
                            4 -> "Чт"
                            5 -> "Пт"
                            6 -> "Сб"
                            7 -> "Вс"
                            else -> "?"
                        }
                    }

                    println("$status ${notification.title}")
                    println("   📝 ${notification.message}")
                    println("   ⏰ ${notification.time} ($days)")
                    println("   🔧 ID: ${notification.id}")
                    println()
                }
            }

            println("\n💡 КОМАНДЫ:")
            println("  toggle <id> - включить/выключить уведомление")
            println("  sim <час> <минута> <день_недели> - симуляция")
            println("  refresh - обновить список")
            println("  exit - выйти")
        }
    }

    fun handleCommand(command: String) {
        when {
            command.startsWith("toggle ") -> {
                val id = command.removePrefix("toggle ").trim()
                runBlocking {
                    viewModel.toggleNotification(id)
                    println("🔧 Уведомление $id переключено")
                }
            }

            command.startsWith("sim ") -> {
                val parts = command.removePrefix("sim ").split(" ")
                if (parts.size == 3) {
                    val hour = parts[0].toIntOrNull()
                    val minute = parts[1].toIntOrNull()
                    val dayOfWeek = parts[2].toIntOrNull()

                    if (hour != null && minute != null && dayOfWeek != null) {
                        viewModel.simulateNotification(dayOfWeek, hour, minute)
                    } else {
                        println("❌ Неверный формат. Используйте: sim 8 0 1")
                    }
                } else {
                    println("❌ Неверный формат. Используйте: sim <час> <минута> <день_недели>")
                }
            }

            command == "refresh" -> {
                runBlocking {
                    println("🔄 Обновление уведомлений...")
                    viewModel.loadNotifications()
                    println("✅ Уведомления обновлены!")
                }
            }

            command == "exit" -> {
                println("👋 Выход из настроек уведомлений")
            }

            else -> {
                println("❓ Неизвестная команда: '$command'")
                println("   Введите 'help' для списка команд")
            }
        }
    }
}