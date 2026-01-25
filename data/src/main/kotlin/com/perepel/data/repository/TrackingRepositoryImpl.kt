package com.perepel.data.repository

import com.perepel.domain.model.DayRecord
import com.perepel.domain.repository.TrackingRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate

class TrackingRepositoryImpl : TrackingRepository {

    // Временное хранилище в памяти (позже заменим на Room)
    private val records = mutableMapOf<LocalDate, DayRecord>()

    init {
        // Добавим тестовые данные
        val today = LocalDate.now()
        val yesterday = today.minusDays(1)

        records[today] = DayRecord(
            date = today,
            completedRules = listOf("1", "2", "3"), // Выполненные правила
            note = "Хороший день!"
        )

        records[yesterday] = DayRecord(
            date = yesterday,
            completedRules = listOf("1", "5"),
            note = "Был занят"
        )
    }

    override suspend fun getRecordForDate(date: LocalDate): DayRecord? {
        delay(50)
        return records[date]
    }

    override suspend fun saveRecord(record: DayRecord) {
        delay(100)
        records[record.date] = record
        println("💾 Сохранена запись за ${record.date}")
    }

    override suspend fun getRecordsForMonth(year: Int, month: Int): List<DayRecord> {
        delay(100)
        return records.values.filter { record ->
            record.date.year == year && record.date.monthValue == month
        }.toList()
    }

    override suspend fun markRuleCompleted(date: LocalDate, ruleId: String) {
        delay(50)
        val record = records[date] ?: DayRecord(date, emptyList())

        if (!record.completedRules.contains(ruleId)) {
            val updatedRules = record.completedRules + ruleId
            records[date] = record.copy(completedRules = updatedRules)
            println("✅ Правило $ruleId отмечено выполненным за $date")
        }
    }

    override suspend fun markRuleIncomplete(date: LocalDate, ruleId: String) {
        delay(50)
        val record = records[date] ?: return

        if (record.completedRules.contains(ruleId)) {
            val updatedRules = record.completedRules.filter { it != ruleId }
            records[date] = record.copy(completedRules = updatedRules)
            println("❌ Правило $ruleId отмечено невыполненным за $date")
        }
    }

    override fun observeRecordForDate(date: LocalDate): Flow<DayRecord?> {
        return flow {
            emit(records[date])
            // Здесь позже будем emit'ить обновления из Room
        }
    }
}