package com.practicum.testcleanarchitecture.util

import android.app.Application
import com.practicum.testcleanarchitecture.di.dataModule
import com.practicum.testcleanarchitecture.di.interactorModule
import com.practicum.testcleanarchitecture.di.navigationModule
import com.practicum.testcleanarchitecture.di.repositoryModule
import com.practicum.testcleanarchitecture.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoviesApplication)
            modules(dataModule, repositoryModule, interactorModule, viewModelModule, navigationModule)
        }
    }
}