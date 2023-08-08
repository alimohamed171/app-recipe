package com.example.testrepo.user_data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao, private val favoriteDao: FavoriteDao, private val mealDataDao: MealDataDao) {


    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
    suspend fun getPassword(userMail:String):String{
       return userDao.readPassword(userMail)
    }
    suspend fun getUser(userMail:String ):User?{
        return userDao.readAllData(userMail)
    }

    suspend fun insertMeal(mealId: String, mealName: String)
    {
        mealDataDao.insertMeal(MealData(mealId, mealName))
    }

    suspend fun addToFavorites(mealId: String, userId: Int)
    {
        favoriteDao.addToFavorites(Favorite(mealId, userId))
    }

    suspend fun removeFromFavorites(mealId: String, userId: Int)
    {
        favoriteDao.removeFromFavorites(Favorite(mealId, userId))
    }

    suspend fun getFavorites(userId: Int): List<String>
    {
        return favoriteDao.getFavorites(userId)
    }
}