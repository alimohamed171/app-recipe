package com.example.testrepo.user_data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Insert
    suspend fun addToFavorites(favorite: Favorite)

    @Delete
    suspend fun removeFromFavorites(favorite: Favorite)

    @Query("SELECT favorites.mealId FROM favorites WHERE userId = :userId")
    suspend fun getFavorites(userId: Int): MutableList<String>

    @Query("SELECT favorites.mealId, favorites.userId FROM favorites WHERE userId = :userId AND mealId = :mealId")
    suspend fun isFavorite(mealId: String, userId: Int): Favorite
}