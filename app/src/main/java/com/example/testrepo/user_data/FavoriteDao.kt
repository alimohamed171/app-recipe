package com.example.testrepo.user_data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Insert
    suspend fun addToFavorites(favorite: Favorite)

    @Delete
    suspend fun removeFromFavorites(favorite: Favorite)

    @Query("SELECT favorites.mealId FROM favorites WHERE userId = :userId")
    suspend fun getFavorites(userId: Int): List<String>
}