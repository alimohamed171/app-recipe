package com.example.testrepo.repo

import com.example.testrepo.model.Meals
import com.example.testrepo.network.RemoteDataSource

class MealRepository(val remoteDataSource: RemoteDataSource): Repository {
    override suspend fun getRemoteRandomMeal(): Meals {
        return remoteDataSource.getRandomMeal()
    }

    override suspend fun getMealByName(name: String): Meals {
        return remoteDataSource.searchByName(name)
    }

    override suspend fun getMealsByFirstLetter(letter: Char): Meals {
        return remoteDataSource.searchByFirstLetter(letter)
    }

    override suspend fun getMealById(mealId: String): Meals {
        return remoteDataSource.getMealById(mealId)
    }
}