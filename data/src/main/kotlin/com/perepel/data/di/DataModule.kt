package com.perepel.data.di

import com.perepel.data.repository.RuleRepositoryImpl
import com.perepel.data.repository.TrackingRepositoryImpl
import com.perepel.domain.repository.RuleRepository
import com.perepel.domain.repository.TrackingRepository
import org.koin.dsl.module

val dataModule = module {
    single<RuleRepository> { RuleRepositoryImpl() }
    single<TrackingRepository> { TrackingRepositoryImpl() }
}