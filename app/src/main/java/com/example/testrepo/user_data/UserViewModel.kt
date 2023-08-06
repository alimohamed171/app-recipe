package com.example.testrepo.user_data

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application ) {
    var user: String = ""
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
//        user = User(0, "0", "0", "0")
    }

    fun addUser(user:User){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }
    fun getUser(userMail:String){
//        viewModelScope.launch(Dispatchers.Main) {
////           user = repository.raedUser(userMail, userPass)
//           user = repository.raedUser(userMail)
//            Log.d("asd->", "getUser: launch scope")
//        }
//        Log.d("asd->", "getUser: function scope")

    }

    fun getUserInfo():String{
        return user
    }


}