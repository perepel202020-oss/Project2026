package com.perepel.core.di

import com.perepel.core.Test
import org.koin.dsl.module

val coreModule = module {
    single { Test() }
}