package com.example.testrepo.user_data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application ) {
    lateinit var user: User


    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    fun addUser(user:User){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }
    fun getUser(userMail:String , userPass:String) {
        viewModelScope.launch(Dispatchers.IO) {
          user = repository.raedUser(userMail, userPass)
        }
    }

    fun getUserInfo():User{
        return user
    }


}