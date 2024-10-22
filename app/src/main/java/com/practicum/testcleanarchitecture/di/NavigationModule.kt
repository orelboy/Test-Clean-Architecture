package com.practicum.testcleanarchitecture.di

import com.practicum.testcleanarchitecture.data.core.navigation.Router
import com.practicum.testcleanarchitecture.data.core.navigation.impl.RouterImpl
import org.koin.dsl.module

val navigationModule = module {
    val router = RouterImpl()

    single<Router> { router }
    single { router.navigatorHolder }
}