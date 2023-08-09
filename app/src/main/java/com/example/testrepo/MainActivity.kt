package com.example.testrepo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.testrepo.user_data.login.LoginFragment

class MainActivity : AppCompatActivity() {
    private lateinit var fragmentManager: FragmentManager
    private lateinit var loginFragment: LoginFragment
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        SharedPrefs.init(this)
//        Handler(Looper.getMainLooper()).postDelayed({
//            navController.navigate(R.id.loginFragment)
//        },10000)
    }

//    override fun onBackPressed() {
//        finishAffinity()
//    }



}