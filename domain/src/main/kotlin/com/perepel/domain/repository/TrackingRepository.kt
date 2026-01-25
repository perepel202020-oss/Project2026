package com.perepel.domain.repository

import com.perepel.domain.model.DayRecord
import java.time.LocalDate
import kotlinx.coroutines.flow.Flow

interface TrackingRepository {
    suspend fun getRecordForDate(date: LocalDate): DayRecord?
    suspend fun saveRecord(record: DayRecord)
    suspend fun getRecordsForMonth(year: Int, month: Int): List<DayRecord>
    suspend fun markRuleCompleted(date: LocalDate, ruleId: String)
    suspend fun markRuleIncomplete(date: LocalDate, ruleId: String)

    // Flow для наблюдения за изменениями записей
    fun observeRecordForDate(date: LocalDate): Flow<DayRecord?>
}