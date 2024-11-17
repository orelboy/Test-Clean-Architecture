package com.practicum.testcleanarchitecture.data.converters

import com.practicum.testcleanarchitecture.data.db.entity.MovieEntity
import com.practicum.testcleanarchitecture.data.dto.MovieDto
import com.practicum.testcleanarchitecture.domain.models.Movie

class MovieDbConvertor {
    fun map(movie: MovieDto): MovieEntity {
        return MovieEntity(movie.id, movie.resultType, movie.image, movie.title, movie.description)
    }

    fun map(movie: MovieEntity): Movie {
        return Movie(movie.id, movie.resultType, movie.image, movie.title, movie.description)
    }
}