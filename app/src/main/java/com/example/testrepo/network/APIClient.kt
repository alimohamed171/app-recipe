package com.example.testrepo.network

import com.example.testrepo.model.Meals

object APIClient: RemoteDataSource {
    override suspend fun getRandomMeal(): Meals {
        return BaseRetrofitHelper.retrofit.create(MealAPIService::class.java).getRandomMeal()
    }

    override suspend fun searchByName(name: String): Meals {
        return BaseRetrofitHelper.retrofit.create(MealAPIService::class.java).searchByName(name)
    }

    override suspend fun searchByFirstLetter(letter: Char): Meals {
        return BaseRetrofitHelper.retrofit.create(MealAPIService::class.java).searchByFirstLetter(letter)
    }

    override suspend fun getMealById(mealId: String): Meals {
        return BaseRetrofitHelper.retrofit.create(MealAPIService::class.java).getMealById(mealId)
    }
}