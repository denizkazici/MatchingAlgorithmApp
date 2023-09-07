package com.deniz.term_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var bottomNavView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController


        bottomNavView = findViewById(R.id.bottomNavigationView)
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)
        bottomNavView.setOnItemSelectedListener { item ->
            val currentDestinationId = navController.currentDestination?.id
            val selectedItemd = item.itemId
            Log.d("Navigation", "Current destination ID: $currentDestinationId")
            Log.d("Navigation", "Selected item ID: $selectedItemd")
            if (item.itemId == navController.currentDestination?.id) {
                return@setOnItemSelectedListener false
            }
            NavigationUI.onNavDestinationSelected(item, navHostFragment.navController)
            true
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->

            Log.d("DestinationChanged", "New destination: ${destination.label}")

            when (destination.id) {
                R.id.loginFragment,
                R.id.adminFragment,
                R.id.userFragment,
                R.id.userDetailFragment,
                R.id.buildingFragment,
                R.id.teamFragment,
                R.id.roadFragment,
                R.id.groupFragment-> {
                    bottomNavView.visibility = View.GONE
                }
                else -> {
                    bottomNavView.visibility = View.VISIBLE
                }
            }
        }
    }

}