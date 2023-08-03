package com.example.testrepo.repo

import com.example.testrepo.Meals

interface Repository {
    suspend fun getRemoteRandomMeal(): Meals
    suspend fun getMealByName(name: String): Meals
    suspend fun getMealsByFirstLetter(letter: Char): Meals
}