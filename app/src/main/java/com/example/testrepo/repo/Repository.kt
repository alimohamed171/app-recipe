package com.example.testrepo.repo

import com.example.testrepo.model.Meals

interface Repository {
    suspend fun getRemoteRandomMeal(): Meals
    suspend fun getMealByName(name: String): Meals
    suspend fun getMealsByFirstLetter(letter: Char): Meals
    suspend fun getMealById(mealId: String): Meals
}