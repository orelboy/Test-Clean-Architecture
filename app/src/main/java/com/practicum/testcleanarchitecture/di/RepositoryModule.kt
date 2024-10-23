package com.practicum.testcleanarchitecture.di

import com.practicum.testcleanarchitecture.data.impl.MoviesRepositoryImpl
import com.practicum.testcleanarchitecture.data.impl.SearchHistoryRepositoryImpl
import com.practicum.testcleanarchitecture.data.converters.MovieCastConverter
import com.practicum.testcleanarchitecture.data.impl.NamesRepositoryImpl
import com.practicum.testcleanarchitecture.domain.api.MoviesRepository
import com.practicum.testcleanarchitecture.domain.api.NamesRepository
import com.practicum.testcleanarchitecture.domain.api.SearchHistoryRepository
import org.koin.dsl.module

val repositoryModule = module {

    // Добавили фабрику для конвертера
    factory { MovieCastConverter() }

    single<MoviesRepository> {
        MoviesRepositoryImpl(
            networkClient = get(),
            localStorage =  get(),
            movieCastConverter = get(),
        )
    }

    single<SearchHistoryRepository> {
        SearchHistoryRepositoryImpl()
    }

    single<NamesRepository> {
        NamesRepositoryImpl(get())
    }

}