package com.perepel.domain.di

import com.perepel.domain.usecase.GetRulesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetRulesUseCase(get()) }
}