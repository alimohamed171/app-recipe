package com.example.testrepo

import android.content.Context
import android.content.SharedPreferences

object SharedPrefs {
    private const val PREFS_NAME = "user_data"
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context)
    {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun getCurrentUser(): Int
    {
        if(sharedPreferences.getBoolean("login_status", false))
        {
            return sharedPreferences.getInt("user_id", -1)
        }
        else
        {
            return -1
        }
    }

    fun signIn(id: Int)
    {
        with(sharedPreferences.edit())
        {
            putBoolean("login_status", true)
            putInt("user_id", id)
            commit()
        }
    }

    fun signOut()
    {
        with(sharedPreferences.edit())
        {
            putBoolean("login_status", false)
            commit()
        }
    }
}