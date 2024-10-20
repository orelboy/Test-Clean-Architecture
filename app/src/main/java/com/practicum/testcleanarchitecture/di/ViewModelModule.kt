package com.practicum.testcleanarchitecture.di

import com.practicum.testcleanarchitecture.presentation.cast.MoviesCastViewModel
import com.practicum.testcleanarchitecture.presentation.movies.MoviesSearchViewModel
import com.practicum.testcleanarchitecture.presentation.details.PosterViewModel
import com.practicum.testcleanarchitecture.presentation.details.AboutViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MoviesSearchViewModel(
            context = androidContext(),
            moviesInteractor =  get())
            //application =  androidApplication())
    }

    viewModel {(movieId: String) ->
        AboutViewModel(movieId, get())
    }

    viewModel {(posterUrl: String) ->
        PosterViewModel(posterUrl)
    }

    viewModel { (movieId: String) ->
        MoviesCastViewModel(movieId, get())
    }
}