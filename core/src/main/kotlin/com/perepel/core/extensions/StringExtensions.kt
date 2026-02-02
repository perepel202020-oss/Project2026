package com.perepel.core.extensions

// Проверка на пустоту с учетом пробелов
fun String?.isNotNullOrBlank(): Boolean = !this.isNullOrBlank()

// Безопасное преобразование в Int
fun String?.toIntOrZero(): Int = this?.toIntOrNull() ?: 0

// Обрезать строку если слишком длинная
fun String.truncate(maxLength: Int, suffix: String = "..."): String {
    return if (this.length > maxLength) {
        this.substring(0, maxLength) + suffix
    } else {
        this
    }
}