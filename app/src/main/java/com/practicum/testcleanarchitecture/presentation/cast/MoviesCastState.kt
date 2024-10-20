package com.practicum.testcleanarchitecture.presentation.cast

import com.practicum.testcleanarchitecture.data.core.ui.RVItem
import com.practicum.testcleanarchitecture.domain.models.MovieCast

sealed interface MoviesCastState {

    object Loading : MoviesCastState

    // Вместо объекта MovieCast появились два поля
    data class Content(
        val fullTitle: String,
        // Поменяли тип ячеек на более общий
        val items: List<RVItem>,
    ) : MoviesCastState

    data class Error(
        val message: String,
    ) : MoviesCastState

}