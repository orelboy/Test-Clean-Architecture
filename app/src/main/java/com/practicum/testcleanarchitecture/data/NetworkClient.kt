package com.practicum.testcleanarchitecture.data

import com.practicum.testcleanarchitecture.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response

}