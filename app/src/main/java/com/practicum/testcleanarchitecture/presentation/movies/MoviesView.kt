package com.practicum.testcleanarchitecture.presentation.movies

import com.practicum.testcleanarchitecture.domain.models.Movie

interface MoviesView {
    // Сюда мы будем добавлять методы
    // для взаимодействия View и Presenter

    fun showPlaceholderMessage(isVisible: Boolean)

    fun showMoviesList(isVisible: Boolean)

    fun showProgressBar(isVisible: Boolean)

    fun changePlaceholderText(newPlaceholderText: String)

    fun updateMoviesList(newMoviesList: List<Movie>)

    fun showMessage(message: String)

}
