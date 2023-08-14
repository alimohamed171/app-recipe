package com.example.testrepo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
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

        setToolBar()
        NavigationUI.setupActionBarWithNavController(this, navController)
        NavigationUI.setupWithNavController(bottomNav, navController)
        NavigationUI.setupWithNavController(toolbar, navController)
        barsVisibility()
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
                finishAffinity()
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
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
                R.id.homeFragment -> {
                    supportActionBar?.title = "Home"
                    toolbar.visibility = View.VISIBLE
                    bottomNav.visibility = View.VISIBLE
                }
                R.id.favoriteFragment -> {
                    supportActionBar?.title = "Favorites"
                    toolbar.visibility = View.VISIBLE
                    bottomNav.visibility = View.VISIBLE
                }
                R.id.searchFragment -> {
                    supportActionBar?.title = "Search"
                    toolbar.visibility = View.VISIBLE
                    bottomNav.visibility = View.VISIBLE
                }

            }
        }
    }

    override fun onBackPressed() {
        if(navController.currentDestination?.id == R.id.homeFragment)
        {
            finishAffinity()
        }
        else
        {
            super.onBackPressed()
        }

    }

    private fun setToolBar() {
        setSupportActionBar(toolbar)
    }
}