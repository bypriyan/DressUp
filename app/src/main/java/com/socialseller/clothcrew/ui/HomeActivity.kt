package com.socialseller.clothcrew.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.activity.StoreLocationActivity
import com.socialseller.clothcrew.adapter.NavDrawerAdapter
import com.socialseller.clothcrew.databinding.ActivityHomeBinding
import com.socialseller.clothcrew.model.NavMenuItem

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
//    private lateinit var navigationView: NavigationView
    lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var recyclerView: RecyclerView

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

        binding.locationStore.setOnClickListener {
            startActivity(Intent(this, StoreLocationActivity::class.java))
        }
    }

}