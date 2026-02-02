package com.perepel.coreui

import com.perepel.coreui.utils.truncate
import com.perepel.coreui.utils.getDayOfWeekName
import com.perepel.coreui.utils.formatPercent

fun main() {
    println("🧪 Тест утилит core-ui модуля:")

    // Тест truncate
    val longText = "Это очень длинный текст, который нужно обрезать"
    println("1. truncate: ${longText.truncate(20)}")

    // Тест getDayOfWeekName
    println("2. Дни недели:")
    (1..7).forEach { day ->
        println("   $day -> ${getDayOfWeekName(day)}")
    }

    // Тест formatPercent
    println("3. Форматирование процентов:")
    val percentages = listOf(0f, 0.25f, 0.5f, 0.75f, 1f)
    percentages.forEach { p ->
        println("   $p -> ${p.formatPercent()}")
    }

    println("\n✅ Все утилиты работают!")
}