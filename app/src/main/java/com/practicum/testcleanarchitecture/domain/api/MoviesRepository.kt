package com.practicum.testcleanarchitecture.domain.api

import com.practicum.testcleanarchitecture.domain.models.Movie

interface MoviesRepository {
    fun searchMovies(expression: String): List<Movie>
}