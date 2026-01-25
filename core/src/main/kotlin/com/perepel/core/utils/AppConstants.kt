package com.perepel.core.utils

object AppConstants {
    // Количество правил в приложении
    const val RULES_COUNT = 7

    // Имена файлов и папок
    const val DATABASE_NAME = "project2026.db"
    const val PREFERENCES_NAME = "project2026_prefs"
    const val EXPORT_FILE_NAME = "project2026_export.json"

    // Ключи для SharedPreferences/DataStore
    object PrefKeys {
        const val FIRST_LAUNCH = "first_launch"
        const val NOTIFICATION_TIME = "notification_time"
        const val BIOMETRIC_ENABLED = "biometric_enabled"
        const val LAST_RESET_TIME = "last_reset_time"
    }

    // Время уведомлений по умолчанию
    const val DEFAULT_NOTIFICATION_HOUR = 20 // 20:00
    const val DEFAULT_NOTIFICATION_MINUTE = 0
}