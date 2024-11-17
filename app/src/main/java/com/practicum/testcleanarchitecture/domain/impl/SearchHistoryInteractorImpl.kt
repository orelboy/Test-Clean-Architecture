package com.practicum.testcleanarchitecture.domain.impl

import com.practicum.testcleanarchitecture.domain.api.SearchHistoryInteractor
import com.practicum.testcleanarchitecture.domain.api.SearchHistoryRepository
import com.practicum.testcleanarchitecture.domain.models.Movie
import kotlinx.coroutines.flow.Flow

class SearchHistoryInteractorImpl(
    private val historyRepository: SearchHistoryRepository
) : SearchHistoryInteractor {
    override fun historyMovies(): Flow<List<Movie>> {
        return historyRepository.historyMovies()
    }
}
