package com.perepel.app.di

import com.perepel.core.di.coreModule
import com.perepel.data.di.dataModule
import com.perepel.domain.di.domainModule
import com.perepel.feature.notifications.di.notificationsModule
import com.perepel.feature.rules.di.rulesModule
import com.perepel.feature.settings.di.settingsModule
import com.perepel.feature.statistics.di.statisticsModule
import com.perepel.feature.tracking.di.trackingModule
import org.koin.dsl.module

val appModule = module {
    includes(
        coreModule,
        domainModule,
        dataModule,
        rulesModule,
        trackingModule,
        statisticsModule,
        settingsModule,
        notificationsModule
    )
}

// Дополнительный модуль для фиктивных зависимостей, которые ещё не имеют своих модулей
val manualModule = module {
    // Пока оставим пустым - будем добавлять по мере создания модулей
}