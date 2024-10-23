package com.practicum.testcleanarchitecture.domain.api

import com.practicum.testcleanarchitecture.domain.models.Person
import com.practicum.testcleanarchitecture.util.Resource

interface NamesRepository {
    fun searchNames(expression: String): Resource<List<Person>>

}