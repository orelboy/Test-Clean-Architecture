package com.practicum.testcleanarchitecture.di

import com.practicum.testcleanarchitecture.presentation.movies.MoviesViewModel
import com.practicum.testcleanarchitecture.presentation.poster.PosterViewModel
import com.practicum.testcleanarchitecture.ui.poster.AboutViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MoviesViewModel(androidContext(), get())
    }

    viewModel {(movieId: String) ->
        AboutViewModel(movieId, get())
    }

    viewModel {(posterUrl: String) ->
        PosterViewModel(posterUrl)
    }

}