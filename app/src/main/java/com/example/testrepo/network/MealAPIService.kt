package com.example.testrepo.network

import com.example.testrepo.model.Meals
import retrofit2.http.GET
import retrofit2.http.Query

interface MealAPIService {
    @GET("api/json/v1/1/random.php")
    suspend fun getRandomMeal(): Meals

    @GET("api/json/v1/1/search.php")
    suspend fun searchByName(@Query("s") name: String): Meals

    @GET("api/json/v1/1/search.php")
    suspend fun searchByFirstLetter(@Query("f") letter: Char): Meals
}