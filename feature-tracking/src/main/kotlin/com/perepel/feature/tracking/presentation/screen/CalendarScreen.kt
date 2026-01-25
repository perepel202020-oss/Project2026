package com.perepel.feature.tracking.presentation.screen

import com.perepel.coreui.components.DayCellView
import com.perepel.domain.model.DayRecord
import com.perepel.feature.tracking.presentation.viewmodel.CalendarViewModel
import com.perepel.feature.tracking.presentation.viewmodel.CalendarUiState
import kotlinx.coroutines.runBlocking
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalendarScreen(
    private val viewModel: CalendarViewModel
) {

    fun displayCurrentMonth() {
        println("=".repeat(70))
        println("📅 КАЛЕНДАРЬ ТРЕКИНГА")
        println("=".repeat(70))

        runBlocking {
            viewModel.loadCurrentMonth()

            when (val state = viewModel.uiState.value) {
                is CalendarUiState.Loading -> {
                    println("Загрузка данных календаря...")
                }
                is CalendarUiState.Success -> {
                    displayMonthCalendar(viewModel.currentMonth.value, state.records)
                }
                is CalendarUiState.Error -> {
                    println("Ошибка: ${state.message}")
                }
            }
        }
    }

    private fun displayMonthCalendar(month: YearMonth, records: Map<LocalDate, DayRecord>) {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        println("\n${month.format(formatter).uppercase()}")
        println("-".repeat(70))

        // Заголовок дней недели
        val daysOfWeek = listOf("ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ", "ВС")
        println(daysOfWeek.joinToString("  "))
        println("-".repeat(70))

        // Получаем первый день месяца и его день недели
        val firstDayOfMonth = month.atDay(1)
        val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7 // Пн=0, Вс=6

        // Создаём календарную сетку
        val daysInMonth = month.lengthOfMonth()
        val weeks = mutableListOf<List<DayCellView?>>()
        var currentWeek = mutableListOf<DayCellView?>()

        // Добавляем пустые ячейки до первого дня
        repeat(firstDayOfWeek) {
            currentWeek.add(null)
        }

        // Добавляем дни месяца
        for (day in 1..daysInMonth) {
            val date = month.atDay(day)
            val isToday = date == LocalDate.now()
            val record = records[date]
            val completedCount = record?.completedRules?.size ?: 0

            val dayCell = DayCellView(
                date = date,
                isToday = isToday,
                completedCount = completedCount,
                totalCount = 7, // Всего правил
                hasNote = record?.note != null,
                mood = record?.mood
            )

            currentWeek.add(dayCell)

            // Если неделя заполнена (7 дней), начинаем новую
            if (currentWeek.size == 7) {
                weeks.add(currentWeek)
                currentWeek = mutableListOf()
            }
        }

        // Добавляем последнюю неделю, если она не пустая
        if (currentWeek.isNotEmpty()) {
            // Заполняем оставшиеся дни недели пустыми ячейками
            while (currentWeek.size < 7) {
                currentWeek.add(null)
            }
            weeks.add(currentWeek)
        }

        // Отображаем календарь (без цветов для простоты)
        weeks.forEach { week ->
            val weekLine = week.map { cell ->
                cell?.let {
                    val display = if (it.isToday) "[${it.getDisplayText()}]" else " ${it.getDisplayText()} "
                    // Простая текстовая индикация
                    when {
                        it.isToday -> "(${it.getDisplayText()})"
                        it.completedCount == it.totalCount && it.totalCount > 0 -> "*${it.getDisplayText()}*"
                        it.getCompletionRate() >= 0.5 -> "+${it.getDisplayText()}+"
                        else -> " ${it.getDisplayText()} "
                    }
                } ?: "    "
            }
            println(weekLine.joinToString(" "))
        }

        // Легенда
        println("\n" + "-".repeat(70))
        println("📊 ЛЕГЕНДА:")
        println("  (ДД) - сегодня")
        println("  *ДД* - все правила выполнены")
        println("  +ДД+ - выполнено >50%")
        println("   ДД  - выполнено <50%")

        // Статистика месяца
        val completedDays = records.count { it.value.completedRules.isNotEmpty() }
        val totalPossible = daysInMonth
        val completionRate = if (totalPossible > 0) (completedDays * 100 / totalPossible) else 0

        println("\n📈 СТАТИСТИКА МЕСЯЦА:")
        println("  Дней с выполненными правилами: $completedDays/$totalPossible ($completionRate%)")

        // Поиск лучшей серии (streak)
        val bestStreak = calculateBestStreak(records.keys.sorted())
        println("  Лучшая серия: $bestStreak дней подряд")

        println("\n💡 КОМАНДЫ: prev/next - навигация, exit - выход")
    }

    private fun calculateBestStreak(dates: List<LocalDate>): Int {
        if (dates.isEmpty()) return 0

        var currentStreak = 1
        var bestStreak = 1

        for (i in 1 until dates.size) {
            if (dates[i].minusDays(1) == dates[i - 1]) {
                currentStreak++
                bestStreak = maxOf(bestStreak, currentStreak)
            } else {
                currentStreak = 1
            }
        }

        return bestStreak
    }

    // Вспомогательный метод для DayCellView
    private fun DayCellView.getCompletionRate(): Float {
        return if (totalCount > 0) completedCount.toFloat() / totalCount else 0f
    }
}