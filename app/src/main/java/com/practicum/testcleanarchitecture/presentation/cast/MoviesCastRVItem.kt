package com.practicum.testcleanarchitecture.presentation.cast

import com.practicum.testcleanarchitecture.domain.models.MovieCastPerson

sealed interface MoviesCastRVItem {

    data class HeaderItem(
        val headerText: String,
    ) : MoviesCastRVItem

    data class PersonItem(
        val data: MovieCastPerson,
    ) : MoviesCastRVItem

}
