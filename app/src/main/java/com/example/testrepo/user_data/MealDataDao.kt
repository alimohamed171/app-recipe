package com.example.testrepo.user_data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface MealDataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMeal(mealData: MealData)
}