package com.practicum.testcleanarchitecture.presentation.names.models

import com.practicum.testcleanarchitecture.domain.models.Person

sealed interface NamesState {

    object Loading : NamesState

    data class Content(
        val persons: List<Person>
    ) : NamesState

    data class Error(
        val message: String
    ) : NamesState

    data class Empty(
        val message: String
    ) : NamesState

}