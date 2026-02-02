package com.perepel.feature.tracking.di

import com.perepel.feature.tracking.presentation.viewmodel.CalendarViewModel
import org.koin.dsl.module

val trackingModule = module {
    // ViewModel
    factory { CalendarViewModel(get()) }
}