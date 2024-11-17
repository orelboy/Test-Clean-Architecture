package com.practicum.testcleanarchitecture.data.impl

import com.practicum.testcleanarchitecture.data.converters.MovieDbConvertor
import com.practicum.testcleanarchitecture.data.db.AppDatabase
import com.practicum.testcleanarchitecture.data.db.entity.MovieEntity
import com.practicum.testcleanarchitecture.domain.api.SearchHistoryRepository
import com.practicum.testcleanarchitecture.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchHistoryRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val movieDbConvertor: MovieDbConvertor,
): SearchHistoryRepository {
    override fun historyMovies(): Flow<List<Movie>> = flow  {
        val movies = appDatabase.movieDao().getMovies()
        emit(convertFromMovieEntity(movies))
    }
    private fun convertFromMovieEntity(movies: List<MovieEntity>): List<Movie> {
        return movies.map { movie -> movieDbConvertor.map(movie) }
    }
}