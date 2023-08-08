package com.example.testrepo.user_data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class MealData(
    @PrimaryKey
    val mealId: String,
    val mealName: String
)