package com.example.testrepo.network

import com.example.testrepo.Meals

interface RemoteDataSource {
    suspend fun getRandomMeal(): Meals
    suspend fun searchByName(name: String): Meals
    suspend fun searchByFirstLetter(letter: Char): Meals
}