package com.practicum.testcleanarchitecture.data.impl

import com.practicum.testcleanarchitecture.data.LocalStorage
import com.practicum.testcleanarchitecture.data.NetworkClient
import com.practicum.testcleanarchitecture.data.converters.MovieCastConverter
import com.practicum.testcleanarchitecture.data.converters.MovieDbConvertor
import com.practicum.testcleanarchitecture.data.db.AppDatabase
import com.practicum.testcleanarchitecture.data.dto.MovieCastRequest
import com.practicum.testcleanarchitecture.data.dto.MovieCastResponse
import com.practicum.testcleanarchitecture.data.dto.MovieDetailsRequest
import com.practicum.testcleanarchitecture.data.dto.MovieDetailsResponse
import com.practicum.testcleanarchitecture.data.dto.MovieDto
import com.practicum.testcleanarchitecture.data.dto.MoviesSearchRequest
import com.practicum.testcleanarchitecture.data.dto.MoviesSearchResponse
import com.practicum.testcleanarchitecture.domain.api.MoviesRepository
import com.practicum.testcleanarchitecture.domain.models.Movie
import com.practicum.testcleanarchitecture.domain.models.MovieCast
import com.practicum.testcleanarchitecture.domain.models.MovieDetails
import com.practicum.testcleanarchitecture.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MoviesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val localStorage: LocalStorage,
    private val movieCastConverter: MovieCastConverter,
    //Зависимости db
    private val appDatabase: AppDatabase,
    private val movieDbConvertor: MovieDbConvertor,
    ) : MoviesRepository {

    override fun searchMovies(expression: String): Flow<Resource<List<Movie>>> = flow {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
         when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }

            200 -> {
                val stored = localStorage.getSavedFavorites()
                with(response as MoviesSearchResponse) {
                    val data = results.map{
                        Movie(
                            id = it.id,
                            resultType = it.resultType,
                            image = it.image,
                            title = it.title,
                            description = it.description,
                            inFavorite = stored.contains(it.id))
                    }
                    // Сохраняем список фильмов в базу данных
                    saveMovie(results)
                    emit(Resource.Success(data))
                }
            }

            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }

    // Конвертируем данные из сетевой модели в модель базы данных и сохраняем
    private suspend fun saveMovie(movies: List<MovieDto>) {
        val movieEntities = movies.map { movie -> movieDbConvertor.map(movie) }
        appDatabase.movieDao().insertMovies(movieEntities)
    }

    override fun getMovieDetails(movieId: String): Flow<Resource<MovieDetails>> = flow {
        val response = networkClient.doRequest(MovieDetailsRequest(movieId))
         when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }

            200 -> {
                with(response as MovieDetailsResponse) {
                    emit(
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
                    )
                }
            }

            else -> {
                emit(Resource.Error("Ошибка сервера"))

            }
        }
    }

    // Добавили новый метод для получения состава участников
    override fun getMovieCast(movieId: String): Flow<Resource<MovieCast>> = flow {
        // Поменяли объект dto на нужный Request-объект
        val response = networkClient.doRequest(MovieCastRequest(movieId))
         when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }
            200 -> {
                emit(
                    // Осталось написать конвертацию!
                    Resource.Success(
                        data = movieCastConverter.convert(response as MovieCastResponse)
                    )
                )
            }
            else -> {
                emit(Resource.Error("Ошибка сервера"))
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