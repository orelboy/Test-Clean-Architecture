package com.practicum.testcleanarchitecture.util

import android.content.Context
import com.practicum.testcleanarchitecture.data.MoviesRepositoryImpl
import com.practicum.testcleanarchitecture.data.network.RetrofitNetworkClient
import com.practicum.testcleanarchitecture.domain.api.MoviesInteractor
import com.practicum.testcleanarchitecture.domain.api.MoviesRepository
import com.practicum.testcleanarchitecture.domain.impl.MoviesInteractorImpl
import com.practicum.testcleanarchitecture.presentation.movies.MoviesSearchViewModel
import com.practicum.testcleanarchitecture.presentation.poster.PosterPresenter
import com.practicum.testcleanarchitecture.presentation.poster.PosterView

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun providePosterPresenter(
        posterView: PosterView,
        imageUrl: String
    ): PosterPresenter {
        return PosterPresenter(posterView, imageUrl)
    }
}