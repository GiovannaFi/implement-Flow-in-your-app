package com.example.viewmodel2.network

import com.example.viewmodel2.network.dto.Data
import retrofit2.Response
import retrofit2.http.GET

interface DogApiService {
    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): Response<Data>
}