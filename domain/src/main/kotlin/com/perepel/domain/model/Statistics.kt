package com.perepel.domain.model

data class Statistics(
    val ruleId: String,
    val completionRate: Float, // процент выполнения от 0.0 до 1.0
    val streak: Int, // текущая серия выполненных дней
    val bestStreak: Int, // лучшая серия
    val totalCompleted: Int, // всего выполнено раз
    val lastCompleted: Long? // timestamp последнего выполнения
)