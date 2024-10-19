package com.practicum.testcleanarchitecture.di

import com.practicum.testcleanarchitecture.presentation.movies.MoviesSearchViewModel
import com.practicum.testcleanarchitecture.presentation.poster.PosterViewModel
import com.practicum.testcleanarchitecture.presentation.poster.AboutViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MoviesSearchViewModel(
            moviesInteractor =  get(),
            application =  androidApplication())
    }

    viewModel {(movieId: String) ->
        AboutViewModel(movieId, get())
    }

    viewModel {(posterUrl: String) ->
        PosterViewModel(posterUrl)
    }

}