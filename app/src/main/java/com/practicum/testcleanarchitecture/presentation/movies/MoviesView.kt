package com.practicum.testcleanarchitecture.presentation.movies

import com.practicum.testcleanarchitecture.domain.models.Movie
import com.practicum.testcleanarchitecture.ui.movies.models.MoviesState

interface MoviesView {
    // Сюда мы будем добавлять методы
    // для взаимодействия View и Presenter

    // Методы, меняющие внешний вид экрана
/*
    // Состояние «загрузки»
    fun showLoading()

    // Состояние «ошибки»
    fun showError(errorMessage: String)

    // Состояние «пустого списка»
    fun showEmpty(emptyMessage: String)

    // Состояние «контента»
    fun showContent(movies: List<Movie>)
*/
    fun render(state: MoviesState)

    // Методы «одноразовых событий»

    fun showToast(additionalMessage: String)
}
