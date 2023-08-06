package com.example.testrepo.user_data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {


    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
    suspend fun getPassword(userMail:String):String{
       return userDao.readPassword(userMail)
    }
    suspend fun getUser(userMail:String ):User?{
        return userDao.readAllData(userMail)
    }

}