package com.perepel.domain.repository

import com.perepel.domain.model.Rule
import kotlinx.coroutines.flow.Flow

interface RuleRepository {
    suspend fun getAllRules(): List<Rule>
    suspend fun getRuleById(id: String): Rule?
    suspend fun saveRule(rule: Rule)
    suspend fun deleteRule(id: String)
    suspend fun updateRule(rule: Rule)

    // Flow для наблюдения за изменениями
    fun observeRules(): Flow<List<Rule>>
}