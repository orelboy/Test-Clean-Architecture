package com.practicum.testcleanarchitecture.domain.api

import com.practicum.testcleanarchitecture.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface SearchHistoryInteractor {
    fun historyMovies(): Flow<List<Movie>>
}