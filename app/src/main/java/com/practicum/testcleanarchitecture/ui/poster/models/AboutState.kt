package com.practicum.testcleanarchitecture.ui.poster.models

import com.practicum.testcleanarchitecture.domain.models.MovieDetails

sealed interface AboutState {

    data class Content(
        val movie: MovieDetails
    ) : AboutState

    data class Error(
        val message: String
    ) : AboutState

}