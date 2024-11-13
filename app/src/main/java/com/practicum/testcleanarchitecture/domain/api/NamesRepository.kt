package com.practicum.testcleanarchitecture.domain.api

import com.practicum.testcleanarchitecture.domain.models.Person
import com.practicum.testcleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow

interface NamesRepository {
    fun searchNames(expression: String): Flow<Resource<List<Person>>>

}