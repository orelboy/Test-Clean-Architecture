package com.practicum.testcleanarchitecture.di

import com.practicum.testcleanarchitecture.domain.api.MoviesInteractor
import com.practicum.testcleanarchitecture.domain.api.NamesInteractor
import com.practicum.testcleanarchitecture.domain.api.SearchHistoryInteractor
import com.practicum.testcleanarchitecture.domain.impl.MoviesInteractorImpl
import com.practicum.testcleanarchitecture.domain.impl.NamesInteracrorImpl
import com.practicum.testcleanarchitecture.domain.impl.SearchHistoryInteractorImpl
import org.koin.dsl.module

val interactorModule = module {

    single<MoviesInteractor> {
        MoviesInteractorImpl(
            repository = get()
        )
    }

    single<SearchHistoryInteractor> {
        SearchHistoryInteractorImpl()
    }

    single<NamesInteractor> {
        NamesInteracrorImpl(get())
    }

}