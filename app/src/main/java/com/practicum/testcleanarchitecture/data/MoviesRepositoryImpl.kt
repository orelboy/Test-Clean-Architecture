package com.practicum.testcleanarchitecture.data

import com.practicum.testcleanarchitecture.data.converters.MovieCastConverter
import com.practicum.testcleanarchitecture.data.dto.MovieCastRequest
import com.practicum.testcleanarchitecture.data.dto.MovieCastResponse
import com.practicum.testcleanarchitecture.data.dto.MovieDetailsRequest
import com.practicum.testcleanarchitecture.data.dto.MovieDetailsResponse
import com.practicum.testcleanarchitecture.data.dto.MoviesSearchRequest
import com.practicum.testcleanarchitecture.data.dto.MoviesSearchResponse
import com.practicum.testcleanarchitecture.domain.api.MoviesRepository
import com.practicum.testcleanarchitecture.domain.models.Movie
import com.practicum.testcleanarchitecture.domain.models.MovieCast
import com.practicum.testcleanarchitecture.domain.models.MovieCastPerson
import com.practicum.testcleanarchitecture.domain.models.MovieDetails
import com.practicum.testcleanarchitecture.util.Resource

class MoviesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val localStorage: LocalStorage,
    private val movieCastConverter: MovieCastConverter,
    ) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }

            200 -> {
                val stored = localStorage.getSavedFavorites()

                Resource.Success((response as MoviesSearchResponse).results.map {
                    Movie(
                        id = it.id,
                        resultType = it.resultType,
                        image = it.image,
                        title = it.title,
                        description = it.description,
                        inFavorite = stored.contains(it.id))
                })
            }

            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }

    override fun getMovieDetails(movieId: String): Resource<MovieDetails> {
        val response = networkClient.doRequest(MovieDetailsRequest(movieId))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }

            200 -> {
                with(response as MovieDetailsResponse) {
                    Resource.Success(
                        MovieDetails(
                            id = id,
                            title = title,
                            imDbRating = imDbRating,
                            year = year,
                            countries = countries,
                            genres = genres,
                            directors = directors,
                            writers = writers,
                            stars = stars,
                            plot = plot,
                        )
                    )
                }
            }

            else -> {
                Resource.Error("Ошибка сервера")

            }
        }
    }

    // Добавили новый метод для получения состава участников
    override fun getMovieCast(movieId: String): Resource<MovieCast> {
        // Поменяли объект dto на нужный Request-объект
        val response = networkClient.doRequest(MovieCastRequest(movieId))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                // Осталось написать конвертацию!
                Resource.Success(
                    data = movieCastConverter.convert(response as MovieCastResponse)
                )
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }


    override fun addMovieToFavorites(movie: Movie) {
        localStorage.addToFavorites(movie.id)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        localStorage.removeFromFavorites(movie.id)
    }
}