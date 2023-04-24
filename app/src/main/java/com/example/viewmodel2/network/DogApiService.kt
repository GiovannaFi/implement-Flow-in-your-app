package com.example.viewmodel1.network

import com.example.viewmodel1.network.dto.Data
import retrofit2.Response
import retrofit2.http.GET

interface DogApiService {
    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): Response<Data>
}