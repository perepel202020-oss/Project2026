package com.perepel.data.repository

import com.perepel.domain.model.Rule
import com.perepel.domain.repository.RuleRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RuleRepositoryImpl : RuleRepository {

    // Мок-данные для тестирования (позже заменим на Room)
    private val mockRules = listOf(
        Rule(
            id = "1",
            title = "Ранний подъем",
            description = "Проснуться до 7:00 утра",
            order = 1
        ),
        Rule(
            id = "2",
            title = "Зарядка",
            description = "15 минут физических упражнений",
            order = 2
        ),
        Rule(
            id = "3",
            title = "Здоровый завтрак",
            description = "Питательный завтрак без фастфуда",
            order = 3
        ),
        Rule(
            id = "4",
            title = "Работа/Учёба",
            description = "Сфокусированная работа 4+ часа",
            order = 4
        ),
        Rule(
            id = "5",
            title = "Спорт",
            description = "Тренировка 30+ минут",
            order = 5
        ),
        Rule(
            id = "6",
            title = "Чтение",
            description = "30 минут чтения книги",
            order = 6
        ),
        Rule(
            id = "7",
            title = "Ранний отход ко сну",
            description = "Лечь спать до 23:00",
            order = 7
        )
    )

    override suspend fun getAllRules(): List<Rule> {
        // Имитация задержки сети/БД
        delay(100)
        return mockRules
    }

    override suspend fun getRuleById(id: String): Rule? {
        delay(50)
        return mockRules.find { it.id == id }
    }

    override suspend fun saveRule(rule: Rule) {
        delay(100)
        println("📝 Сохранено правило: ${rule.title}")
        // Здесь позже будет логика сохранения в БД
    }

    override suspend fun deleteRule(id: String) {
        delay(100)
        println("🗑️ Удалено правило с id: $id")
        // Здесь позже будет логика удаления из БД
    }

    override suspend fun updateRule(rule: Rule) {
        delay(100)
        println("🔄 Обновлено правило: ${rule.title}")
        // Здесь позже будет логика обновления в БД
    }

    override fun observeRules(): Flow<List<Rule>> {
        // Flow для реактивного наблюдения за изменениями
        return flow {
            emit(mockRules)
            // Здесь позже будем emit'ить обновления из Room
        }
    }
}