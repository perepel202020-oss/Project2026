package com.perepel.core.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object DateUtils {

    // Форматы дат
    val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    // Получить сегодняшнюю дату
    fun today(): LocalDate = LocalDate.now()

    // Проверить, является ли дата сегодняшней
    fun isToday(date: LocalDate): Boolean = date == today()

    // Получить время сброса (00:00)
    fun getResetTime(): LocalTime = LocalTime.MIDNIGHT

    // Форматировать дату в строку
    fun formatDate(date: LocalDate): String = date.format(DATE_FORMATTER)

    // Получить начало дня
    fun startOfDay(date: LocalDate): LocalDateTime = date.atStartOfDay()

    // Получить конец дня
    fun endOfDay(date: LocalDate): LocalDateTime = date.atTime(LocalTime.MAX)

    // Проверить, нужно ли выполнить сброс (прошла ли полночь)
    fun shouldReset(lastCheck: LocalDateTime): Boolean {
        val now = LocalDateTime.now()
        return now.toLocalDate() > lastCheck.toLocalDate()
    }
}