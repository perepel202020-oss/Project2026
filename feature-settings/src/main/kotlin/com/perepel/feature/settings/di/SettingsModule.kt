package com.perepel.feature.settings.di

import com.perepel.feature.settings.presentation.viewmodel.SettingsViewModel
import org.koin.dsl.module

val settingsModule = module {
    factory { SettingsViewModel() }
}