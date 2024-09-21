package com.practicum.testcleanarchitecture

import android.app.Activity
import com.practicum.testcleanarchitecture.data.MoviesRepositoryImpl
import com.practicum.testcleanarchitecture.data.network.RetrofitNetworkClient
import com.practicum.testcleanarchitecture.domain.api.MoviesInteractor
import com.practicum.testcleanarchitecture.domain.api.MoviesRepository
import com.practicum.testcleanarchitecture.domain.impl.MoviesInteractorImpl
import com.practicum.testcleanarchitecture.presentation.MoviesSearchController
import com.practicum.testcleanarchitecture.presentation.PosterController
import com.practicum.testcleanarchitecture.ui.movies.MoviesAdapter

object Creator {
    private fun getMoviesRepository(): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient())
    }

    fun provideMoviesInteractor(): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository())
    }

    fun provideMoviesSearchController(activity: Activity, adapter: MoviesAdapter): MoviesSearchController {
        return MoviesSearchController(activity, adapter)
    }

    fun providePosterController(activity: Activity): PosterController {
        return PosterController(activity)
    }
}