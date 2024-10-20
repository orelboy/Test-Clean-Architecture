package com.practicum.testcleanarchitecture.presentation.cast

import com.practicum.testcleanarchitecture.domain.models.MovieCast

sealed interface MoviesCastState {

    object Loading : MoviesCastState

    // Вместо объекта MovieCast появились два поля
    data class Content(
        val fullTitle: String,
        val items: List<MoviesCastRVItem>,
    ) : MoviesCastState

    data class Error(
        val message: String,
    ) : MoviesCastState

}