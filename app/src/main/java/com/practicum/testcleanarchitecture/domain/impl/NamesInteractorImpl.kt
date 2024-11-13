package com.practicum.testcleanarchitecture.domain.impl

import com.practicum.testcleanarchitecture.domain.api.NamesInteractor
import com.practicum.testcleanarchitecture.domain.api.NamesRepository
import com.practicum.testcleanarchitecture.domain.models.Person
import com.practicum.testcleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NamesInteracrorImpl(private val repository: NamesRepository) : NamesInteractor {

    override fun searchNames(expression: String): Flow<Pair<List<Person>?, String?>> {
        return repository.searchNames(expression).map { result ->
            when (result) {
                is Resource.Success -> {
                    Pair(result.data, null)
                }

                is Resource.Error -> {
                    Pair(null, result.message)
                }
            }
        }
    }
}