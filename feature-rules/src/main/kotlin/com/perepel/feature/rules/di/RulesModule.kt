package com.perepel.feature.rules.di

import com.perepel.feature.rules.presentation.viewmodel.RulesViewModel
import org.koin.dsl.module

val rulesModule = module {
    // ViewModel
    factory { RulesViewModel(get()) }
}