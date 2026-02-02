package com.perepel.coreui.components

/**
 * Компонент прогресс-бара для отображения выполнения правил за день
 */
class ProgressBarDay {

    data class State(
        val completed: Int,
        val total: Int,
        val progress: Float,
        val text: String
    )

    fun calculateState(completed: Int, total: Int): State {
        val progress = if (total > 0) completed.toFloat() / total else 0f
        val text = formatProgressText(completed, total)

        return State(
            completed = completed,
            total = total,
            progress = progress,
            text = text
        )
    }

    private fun formatProgressText(completed: Int, total: Int): String {
        val percentage = if (total > 0) (completed * 100 / total) else 0
        return "$completed/$total ($percentage%)"
    }

    fun getEmojiForProgress(progress: Float): String {
        return when {
            progress >= 1.0f -> "🎯" // Все выполнено
            progress >= 0.8f -> "🔥" // Отлично
            progress >= 0.6f -> "👍" // Хорошо
            progress >= 0.4f -> "😐" // Средне
            progress >= 0.2f -> "🤔" // Плохо
            else -> "😴" // Очень плохо
        }
    }
}