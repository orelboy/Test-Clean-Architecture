package com.practicum.testcleanarchitecture.data.impl

import com.practicum.testcleanarchitecture.data.NetworkClient
import com.practicum.testcleanarchitecture.data.dto.NamesSearchRequest
import com.practicum.testcleanarchitecture.data.dto.NamesSearchResponse
import com.practicum.testcleanarchitecture.domain.api.NamesRepository
import com.practicum.testcleanarchitecture.domain.models.Person
import com.practicum.testcleanarchitecture.util.Resource

class NamesRepositoryImpl (private val networkClient: NetworkClient) : NamesRepository {

    override fun searchNames(expression: String): Resource<List<Person>> {
        val response = networkClient.doRequest(NamesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }

            200 -> {
                with(response as NamesSearchResponse) {
                    Resource.Success(results.map {
                        Person(
                            id = it.id,
                            name = it.title,
                            description = it.description,
                            photoUrl = it.image
                        )
                    })
                }
            }

            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
}