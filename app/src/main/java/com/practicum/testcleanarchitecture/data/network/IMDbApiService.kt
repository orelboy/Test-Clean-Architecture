package com.practicum.testcleanarchitecture.data.network

import com.practicum.testcleanarchitecture.data.dto.MoviesSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApiService {
    @GET("/en/API/SearchMovie/k_zcuw1ytf/{expression}")
    fun searchMovies(
        @Path("expression") expression: String
    ): Call<MoviesSearchResponse>
}