package com.perepel.feature.notifications.di

import com.perepel.feature.notifications.data.NotificationRepositoryImpl
import com.perepel.feature.notifications.domain.NotificationRepository
import com.perepel.feature.notifications.domain.NotificationScheduler
import com.perepel.feature.notifications.presentation.viewmodel.NotificationViewModel
import org.koin.dsl.module

val notificationsModule = module {
    // Репозиторий
    single<NotificationRepository> { NotificationRepositoryImpl() }

    // Scheduler
    single {
        NotificationScheduler(
            repository = get(),
            onNotification = { notification ->
                println("\n🔔 УВЕДОМЛЕНИЕ: ${notification.title}")
                println("   ${notification.message}")
                println("   Время: ${notification.time}")
            }
        )
    }

    // ViewModel
    factory { NotificationViewModel(get(), get()) }
}