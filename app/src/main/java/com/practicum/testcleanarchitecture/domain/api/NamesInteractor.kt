package com.practicum.testcleanarchitecture.domain.api

import com.practicum.testcleanarchitecture.domain.models.Person
import kotlinx.coroutines.flow.Flow

interface NamesInteractor {
    fun searchNames(expression: String): Flow<Pair<List<Person>?, String?>>
}