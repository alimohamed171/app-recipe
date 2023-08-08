package com.example.testrepo.user_data

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "favorites",
    primaryKeys = ["mealId", "userId"],
    foreignKeys = arrayOf(ForeignKey(entity = MealData::class, parentColumns = arrayOf("mealId"), childColumns = arrayOf("mealId"), onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = User::class, parentColumns = arrayOf("id"), childColumns = arrayOf("userId"), onDelete = ForeignKey.CASCADE))
)

data class Favorite (
    val mealId: String,
    val userId: Int
)