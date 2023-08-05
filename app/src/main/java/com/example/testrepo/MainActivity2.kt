package com.example.testrepo

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.testrepo.network.APIClient
import com.example.testrepo.repo.MealRepository
import com.example.testrepo.viewModel.MealViewModel
import com.example.testrepo.viewModel.MealViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity2 : AppCompatActivity() {
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController
    lateinit var toolbar: Toolbar
    lateinit var bottomNav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.in_app_nav_host) as NavHostFragment
        navController = navHostFragment.navController
        toolbar = findViewById(R.id.toolbar)
        bottomNav = findViewById(R.id.bottom_nav)

        bottomNavOnItemSelectedListener()
        barsVisibility()
        setToolBar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about_us, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.about_menu -> navController.navigate(R.id.aboutFragment)
            R.id.sign_out_about -> {
                SharedPrefs.signOut()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun bottomNavOnItemSelectedListener() {
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

    private fun barsVisibility() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id)
            {
                R.id.aboutFragment -> {
                    toolbar.visibility = View.GONE
                    bottomNav.visibility = View.GONE
                }
                R.id.detailFragment -> {
                    toolbar.visibility = View.GONE
                    bottomNav.visibility = View.GONE
                }
                else -> {
                    toolbar.visibility = View.VISIBLE
                    bottomNav.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setToolBar() {
        setSupportActionBar(toolbar)
    }
}