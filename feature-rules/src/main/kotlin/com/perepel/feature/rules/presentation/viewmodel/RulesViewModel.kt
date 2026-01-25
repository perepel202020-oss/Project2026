package com.perepel.feature.rules.presentation.viewmodel

import com.perepel.domain.model.Rule
import com.perepel.domain.usecase.GetRulesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RulesViewModel(
    private val getRulesUseCase: GetRulesUseCase
) {
    private val _uiState = MutableStateFlow<RulesUiState>(RulesUiState.Loading)
    val uiState: StateFlow<RulesUiState> = _uiState.asStateFlow()

    private val _rules = MutableStateFlow<List<Rule>>(emptyList())
    val rules: StateFlow<List<Rule>> = _rules.asStateFlow()

    suspend fun loadRules() {
        _uiState.value = RulesUiState.Loading
        try {
            val rules = getRulesUseCase()
            _rules.value = rules
            _uiState.value = RulesUiState.Success(rules)
        } catch (e: Exception) {
            _uiState.value = RulesUiState.Error(e.message ?: "Unknown error")
        }
    }

    fun toggleRuleCompletion(ruleId: String) {
        val currentRules = _rules.value.toMutableList()
        val ruleIndex = currentRules.indexOfFirst { it.id == ruleId }

        if (ruleIndex != -1) {
            val rule = currentRules[ruleIndex]
            println("Правило '${rule.title}' отмечено выполненным")
            // TODO: Здесь будет логика сохранения в TrackingRepository
        }
    }
}

// Делаем sealed class публичной
sealed class RulesUiState {
    object Loading : RulesUiState()
    data class Success(val rules: List<Rule>) : RulesUiState()
    data class Error(val message: String) : RulesUiState()
}