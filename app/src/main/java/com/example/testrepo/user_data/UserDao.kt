package com.example.testrepo.user_data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.concurrent.Flow

//Data Access Object
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_data WHERE email = :userMail ")
    suspend fun readAllData(userMail:String  ):User?


    @Query("SELECT password FROM user_data WHERE email = :userMail")
    suspend fun readPassword(userMail:String): String

}