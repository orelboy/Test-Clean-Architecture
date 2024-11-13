package com.practicum.testcleanarchitecture.data

import com.practicum.testcleanarchitecture.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response
    suspend fun doRequestSuspend(dto: Any): Response //времено для постепенного перевода запроса на Корутины
}