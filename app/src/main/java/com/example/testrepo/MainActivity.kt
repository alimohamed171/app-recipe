package com.example.testrepo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    lateinit var btnlogin : Button
    private lateinit var fragmentManager: FragmentManager
    private lateinit var loginFragment: LoginFragment
//    private lateinit var transaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//       btnlogin = findViewById(R.id.btnLogin)
//
//        btnlogin.setOnClickListener{
//            val intent = Intent(this,MainActivity2::class.java)
//            startActivity(intent)
//            }

 
    }
}