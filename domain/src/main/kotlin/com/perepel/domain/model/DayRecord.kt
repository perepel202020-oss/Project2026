package com.perepel.domain.model

import java.time.LocalDate

data class DayRecord(
    val date: LocalDate,
    val completedRules: List<String>, // список ID выполненных правил
    val note: String? = null,
    val mood: Int? = null // от 1 до 5, например
) {
    fun isRuleCompleted(ruleId: String): Boolean {
        return completedRules.contains(ruleId)
    }
}