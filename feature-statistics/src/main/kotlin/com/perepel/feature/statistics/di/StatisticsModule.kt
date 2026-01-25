package com.perepel.feature.statistics.di

import com.perepel.feature.statistics.SimpleStatistics
import org.koin.dsl.module

val statisticsModule = module {
    // Statistics
    factory { SimpleStatistics(get(), get()) }
}