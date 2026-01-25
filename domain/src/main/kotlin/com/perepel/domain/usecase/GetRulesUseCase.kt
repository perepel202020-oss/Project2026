package com.perepel.domain.usecase

import com.perepel.domain.model.Rule
import com.perepel.domain.repository.RuleRepository
import kotlinx.coroutines.flow.Flow

class GetRulesUseCase(
    private val repository: RuleRepository
) {
    suspend operator fun invoke(): List<Rule> {
        return repository.getAllRules()
    }

    fun observeRules(): Flow<List<Rule>> {
        return repository.observeRules()
    }
}