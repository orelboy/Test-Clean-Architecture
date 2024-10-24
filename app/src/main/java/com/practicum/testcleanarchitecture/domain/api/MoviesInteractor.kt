package com.practicum.testcleanarchitecture.domain.api

import com.practicum.testcleanarchitecture.domain.models.Movie

interface MoviesInteractor {
    fun searchMovies(expression: String, consumer: MoviesConsumer)

    interface MoviesConsumer {
        fun consume(foundMovies: List<Movie>)
    }
}