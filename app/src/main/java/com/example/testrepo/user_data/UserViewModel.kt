package com.example.testrepo.user_data

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(application: Application):AndroidViewModel(application ) {
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        val mealDataDao = UserDatabase.getDatabase(application).mealDataDao()
        val favoriteDao = UserDatabase.getDatabase(application).favoritesDao()

        repository = UserRepository(userDao, favoriteDao, mealDataDao)
    }

    fun addUser(user:User){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }

    suspend fun getUser(userMail:String):User? {
        return withContext(Dispatchers.IO) {
          repository.getUser(userMail)
        }
    }




//    fun getUserInfo():String{
//        return user
//    }
//    fun getUser(userMail:String){
//        viewModelScope.launch(Dispatchers.Main) {
////           user = repository.raedUser(userMail, userPass)
//           user = repository.raedUser(userMail)
//            Log.d("asd->", "getUser: launch scope")
//        }
//        Log.d("asd->", "getUser: function scope")
//      }
//    fun getUser(userMail:String){
//     viewModelScope.launch(Dispatchers.Main) {
//         _user.postValue(repository.getUser(userMail))
//        }
//    }

}