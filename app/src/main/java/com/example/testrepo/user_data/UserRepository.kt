package com.example.testrepo.user_data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {


    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
    suspend fun raedUser(userMail:String , userPass:String):User{
       return userDao.readAllData(userMail,userPass)
    }


}