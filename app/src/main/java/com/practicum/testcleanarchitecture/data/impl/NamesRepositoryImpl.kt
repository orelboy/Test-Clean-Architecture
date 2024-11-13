package com.practicum.testcleanarchitecture.data.impl

import com.practicum.testcleanarchitecture.data.NetworkClient
import com.practicum.testcleanarchitecture.data.dto.NamesSearchRequest
import com.practicum.testcleanarchitecture.data.dto.NamesSearchResponse
import com.practicum.testcleanarchitecture.domain.api.NamesRepository
import com.practicum.testcleanarchitecture.domain.models.Person
import com.practicum.testcleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NamesRepositoryImpl (private val networkClient: NetworkClient) : NamesRepository {

    override fun searchNames(expression: String): Flow<Resource<List<Person>>> = flow {
        val response = networkClient.doRequestSuspend(NamesSearchRequest(expression))
         when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }

            200 -> {
                with(response as NamesSearchResponse) {
                    val data = results.map {
                        Person(
                            id = it.id,
                            name = it.title,
                            description = it.description,
                            photoUrl = it.image
                        )
                    }
                    emit(Resource.Success(data))
                }
            }

            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }
}