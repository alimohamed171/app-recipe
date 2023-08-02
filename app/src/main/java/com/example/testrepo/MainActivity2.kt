package com.example.testrepo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity2 : AppCompatActivity() {
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.in_app_nav_host) as NavHostFragment
        navController = navHostFragment.navController

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_nav)
        bottomNav.setOnItemSelectedListener {
            when(it.itemId)
            {
                R.id.bottom_nav_home -> navController.navigate(R.id.homeFragment)
                R.id.bottom_nav_favorites -> navController.navigate(R.id.favoriteFragment)
                R.id.bottom_nav_search -> navController.navigate(R.id.searchFragment)
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about_us, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.about_menu -> navController.navigate(R.id.aboutFragment)
            R.id.sign_out_about -> Toast.makeText(this, "You have Signed Out.", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}