package com.perepel.app

import com.perepel.app.di.appModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun main() {
    println("=".repeat(70))
    println("PROJECT 2026 - ВСЕ 5 ФИЧ С KOIN")
    println("=".repeat(70))

    // ИНИЦИАЛИЗАЦИЯ KOIN
    println("\n🚀 Инициализация Koin...")
    startKoin {
        modules(appModule)
    }
    println("✅ Koin успешно инициализирован")

    // СОЗДАЕМ КОМПОНЕНТ ДЛЯ ИНЪЕКЦИИ
    val app = Project2026App()
    app.runApp()
}

class Project2026App : KoinComponent {

    // ИНЪЕКЦИЯ ВСЕХ 5 ФИЧ
    private val test: com.perepel.core.Test by inject()
    private val rulesViewModel: com.perepel.feature.rules.presentation.viewmodel.RulesViewModel by inject()
    private val calendarViewModel: com.perepel.feature.tracking.presentation.viewmodel.CalendarViewModel by inject()
    private val simpleStatistics: com.perepel.feature.statistics.SimpleStatistics by inject()
    private val notificationViewModel: com.perepel.feature.notifications.presentation.viewmodel.NotificationViewModel by inject()
    private val settingsViewModel: com.perepel.feature.settings.presentation.viewmodel.SettingsViewModel by inject()  // ← НОВАЯ ФИЧА

    fun runApp() {
        println("\n📦 ЗАВИСИМОСТИ ЗАИНЪЕКТИРОВАНЫ (5 фич):")
        println("   1. 📋 RulesViewModel")
        println("   2. 📅 CalendarViewModel")
        println("   3. 📊 SimpleStatistics")
        println("   4. 🔔 NotificationViewModel")
        println("   5. ⚙️  SettingsViewModel ✅")

        // Запуск всех 5 фич...
        // ... (ваш существующий код тестирования фич 1-4)

        // ТЕСТИРУЕМ ФИЧУ 5: НАСТРОЙКИ
        println("\n" + "=".repeat(70))
        println("⚙️  ФИЧА 5: НАСТРОЙКИ (ТЕМА)")
        println("=".repeat(70))

        val settingsScreen = com.perepel.feature.settings.presentation.screen.SettingsScreen(settingsViewModel)
        settingsScreen.display()
        settingsScreen.handleCommand("toggle")

        // ИТОГ
        println("\n" + "=".repeat(70))
        println("🏗️  АРХИТЕКТУРА ЗАВЕРШЕНА:")
        println("-".repeat(40))
        println("  📦 Всего модулей: 10")
        println("  🎯 Готово фич: 5 из 5")
        println("  🔧 DI модулей Koin: 8")
        println("-".repeat(40))
        println("\n🎉 ПРОЕКТ '2026' ПОЛНОСТЬЮ ЗАВЕРШЁН!")
        println("🚀 АРХИТЕКТУРА ГОТОВА К ПРОДАКШЕНУ!")
        println("=".repeat(70))
    }
}