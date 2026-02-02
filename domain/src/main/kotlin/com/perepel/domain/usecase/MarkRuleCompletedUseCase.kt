package com.perepel.domain.usecase

import com.perepel.domain.repository.TrackingRepository
import java.time.LocalDate

class MarkRuleCompletedUseCase(
    private val repository: TrackingRepository
) {
    suspend operator fun invoke(date: LocalDate, ruleId: String) {
        repository.markRuleCompleted(date, ruleId)
    }
}