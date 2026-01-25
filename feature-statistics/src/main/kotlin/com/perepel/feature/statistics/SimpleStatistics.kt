package com.perepel.feature.statistics

import com.perepel.domain.repository.RuleRepository
import com.perepel.domain.repository.TrackingRepository
import kotlinx.coroutines.runBlocking
import java.time.LocalDate

class SimpleStatistics(
    private val ruleRepository: RuleRepository,
    private val trackingRepository: TrackingRepository
) {

    fun display() {
        println("=".repeat(70))
        println("📊 ПРОСТАЯ СТАТИСТИКА")
        println("=".repeat(70))

        runBlocking {
            val rules = ruleRepository.getAllRules()
            val today = LocalDate.now()

            // Статистика за последние 7 дней
            val lastWeek = (0..6).map { today.minusDays(it.toLong()) }
            val weekRecords = lastWeek.mapNotNull { date ->
                trackingRepository.getRecordForDate(date)
            }

            println("\n📈 ЗА ПОСЛЕДНЮЮ НЕДЕЛЮ:")
            println("-".repeat(70))

            // Статистика по дням
            lastWeek.forEach { date ->
                val record = trackingRepository.getRecordForDate(date)
                val completed = record?.completedRules?.size ?: 0
                val isToday = date == today
                val dayName = date.dayOfWeek.toString().take(3).uppercase()

                val todayMarker = if (isToday) " [сегодня]" else ""
                println("$dayName ${date.dayOfMonth}: $completed/7 выполнено$todayMarker")
            }

            // Общая статистика
            val totalCompleted = weekRecords.sumOf { it.completedRules.size }
            val totalPossible = rules.size * 7 // 7 дней × 7 правил
            val completionRate = if (totalPossible > 0) (totalCompleted * 100 / totalPossible) else 0

            println("\n📊 ОБЩАЯ СТАТИСТИКА:")
            println("  Выполнено правил: $totalCompleted/$totalPossible ($completionRate%)")

            // Статистика по правилам
            println("\n🎯 ПО ПРАВИЛАМ:")
            rules.forEachIndexed { index, rule ->
                val completedCount = weekRecords.count { record ->
                    record.completedRules.contains(rule.id)
                }
                val percentage = if (7 > 0) (completedCount * 100 / 7) else 0
                val progressBar = "█".repeat(percentage / 10) + "░".repeat(10 - percentage / 10)

                println("${index + 1}. ${rule.title}")
                println("   [$progressBar] $percentage% ($completedCount/7 дней)")
            }

            // Рекомендации
            println("\n💡 РЕКОМЕНДАЦИИ:")
            val bestDay = lastWeek.maxByOrNull { date ->
                trackingRepository.getRecordForDate(date)?.completedRules?.size ?: 0
            }

            bestDay?.let {
                val bestCount = trackingRepository.getRecordForDate(it)?.completedRules?.size ?: 0
                println("  Лучший день: ${it.dayOfMonth} число - $bestCount/7 правил")
            }

            println("  Среднее в день: ${totalCompleted / 7} правил")

            if (completionRate > 70) {
                println("  🎉 Отличные результаты! Так держать!")
            } else if (completionRate > 50) {
                println("  👍 Хорошо! Есть куда расти")
            } else {
                println("  💪 Начинайте с малого - ставьте достижимые цели")
            }
        }
    }
}