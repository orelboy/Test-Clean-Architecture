package com.practicum.testcleanarchitecture.di

import com.practicum.testcleanarchitecture.data.MoviesRepositoryImpl
import com.practicum.testcleanarchitecture.data.SearchHistoryRepositoryImpl
import com.practicum.testcleanarchitecture.domain.api.MoviesRepository
import com.practicum.testcleanarchitecture.domain.api.SearchHistoryRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<MoviesRepository> {
        MoviesRepositoryImpl(
            networkClient = get(),
            localStorage =  get(),
        )
    }

    single<SearchHistoryRepository> {
        SearchHistoryRepositoryImpl()
    }

}