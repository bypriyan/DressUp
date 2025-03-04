package com.socialseller.clothcrew.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.activity.StoreLocationActivity
import com.socialseller.clothcrew.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize DrawerLayout
        drawerLayout = findViewById(R.id.drawerLayout)

        // Initialize Navigation Controller
        navController = Navigation.findNavController(this, R.id.frameLayout)
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

        // Open Drawer when navDrawer icon is clicked
        binding.navDrawer.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.locationStore.setOnClickListener {
            startActivity(Intent(this, StoreLocationActivity::class.java))
        }

        // Handle back button press with OnBackPressedDispatcher
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }
}