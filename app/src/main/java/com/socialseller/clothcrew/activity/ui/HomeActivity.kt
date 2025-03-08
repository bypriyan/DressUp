package com.socialseller.clothcrew.activity.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
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
import com.socialseller.clothcrew.activity.stores.StoreLocationActivity
import com.socialseller.clothcrew.activity.profile.ProfileActivity
import com.socialseller.clothcrew.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    lateinit var bottomNavigationView: BottomNavigationView


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, R.id.frameLayout);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        // Initialize DrawerLayout
        drawerLayout = findViewById(R.id.drawerLayout)

        // Open Drawer when navDrawer icon is clicked
        binding.navDrawer.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Open Drawer when navDrawer icon is clicked
        findViewById<ImageView>(R.id.navDrawer).setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
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

        //clicks
        binding.locationStore.setOnClickListener {
            startActivity(Intent(this, StoreLocationActivity::class.java))
        }

        binding.profile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))

        }
    }

}