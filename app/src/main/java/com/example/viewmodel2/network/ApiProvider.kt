package com.example.viewmodel2.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiProvider {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val dogApiService = retrofit.create(DogApiService::class.java)

    suspend fun getDogData() = dogApiService.getRandomDogImage()

}