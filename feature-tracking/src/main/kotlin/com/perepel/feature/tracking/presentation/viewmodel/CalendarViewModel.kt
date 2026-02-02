package com.perepel.feature.tracking.presentation.viewmodel

import com.perepel.domain.model.DayRecord
import com.perepel.domain.repository.TrackingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.time.YearMonth

class CalendarViewModel(
    private val trackingRepository: TrackingRepository
) {
    private val _uiState = MutableStateFlow<CalendarUiState>(CalendarUiState.Loading)
    val uiState: StateFlow<CalendarUiState> = _uiState.asStateFlow()

    private val _currentMonth = MutableStateFlow(YearMonth.now())
    val currentMonth: StateFlow<YearMonth> = _currentMonth.asStateFlow()

    private val _dailyRecords = MutableStateFlow<Map<LocalDate, DayRecord>>(emptyMap())
    val dailyRecords: StateFlow<Map<LocalDate, DayRecord>> = _dailyRecords.asStateFlow()

    suspend fun loadMonthData(year: Int, month: Int) {
        _uiState.value = CalendarUiState.Loading
        try {
            val records = trackingRepository.getRecordsForMonth(year, month)
            val recordsMap = records.associateBy { it.date }

            _dailyRecords.value = recordsMap
            _currentMonth.value = YearMonth.of(year, month)
            _uiState.value = CalendarUiState.Success(recordsMap)
        } catch (e: Exception) {
            _uiState.value = CalendarUiState.Error(e.message ?: "Ошибка загрузки данных")
        }
    }

    suspend fun loadCurrentMonth() {
        val now = LocalDate.now()
        loadMonthData(now.year, now.monthValue)
    }

    fun navigateToPreviousMonth() {
        val current = _currentMonth.value
        _currentMonth.value = current.minusMonths(1)
    }

    fun navigateToNextMonth() {
        val current = _currentMonth.value
        _currentMonth.value = current.plusMonths(1)
    }

    suspend fun toggleRuleForDate(date: LocalDate, ruleId: String) {
        val record = trackingRepository.getRecordForDate(date)
        val isCompleted = record?.completedRules?.contains(ruleId) ?: false

        if (isCompleted) {
            trackingRepository.markRuleIncomplete(date, ruleId)
        } else {
            trackingRepository.markRuleCompleted(date, ruleId)
        }

        // Обновляем данные после изменения
        loadMonthData(date.year, date.monthValue)
    }

    fun getCompletionRateForDate(date: LocalDate): Float {
        val record = _dailyRecords.value[date]
        val completed = record?.completedRules?.size ?: 0
        // Предполагаем 7 правил (как в фиче rules)
        return if (7 > 0) completed.toFloat() / 7 else 0f
    }
}

sealed class CalendarUiState {
    object Loading : CalendarUiState()
    data class Success(val records: Map<LocalDate, DayRecord>) : CalendarUiState()
    data class Error(val message: String) : CalendarUiState()
}