package com.perepel.coreui.utils

// Делаем функции публичными
public fun String.truncate(maxLength: Int): String {
    return if (length <= maxLength) this else substring(0, maxLength) + "..."
}

public fun Int.toPx(dp: Float, density: Float = 2f): Int {
    return (dp * density).toInt()
}

public fun Float.dpToPx(density: Float = 2f): Float {
    return this * density
}

public fun getDayOfWeekName(dayNumber: Int): String {
    return when (dayNumber) {
        1 -> "Пн"
        2 -> "Вт"
        3 -> "Ср"
        4 -> "Чт"
        5 -> "Пт"
        6 -> "Сб"
        7 -> "Вс"
        else -> "??"
    }
}

// Добавляем вспомогательную функцию для форматирования процентов
public fun Float.formatPercent(): String {
    return "%.1f%%".format(this * 100)
}