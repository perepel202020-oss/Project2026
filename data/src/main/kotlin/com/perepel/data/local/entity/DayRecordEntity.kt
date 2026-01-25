package com.perepel.data.local.entity

import java.time.LocalDate

data class DayRecordEntity(
    val date: LocalDate,
    val completedRules: List<String>,
    val note: String? = null,
    val mood: Int? = null
)