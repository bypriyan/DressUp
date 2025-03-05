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
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.activity.StoreLocationActivity
import com.socialseller.clothcrew.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var navigationView: NavigationView
    private lateinit var headerView: View

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize DrawerLayout
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)

        // Get header view
        headerView = navigationView.getHeaderView(0)
        val userName = headerView.findViewById<TextView>(R.id.name)
        val userEmail = headerView.findViewById<TextView>(R.id.wallet)
        val profileImage = headerView.findViewById<ImageView>(R.id.profile_image)

        // Example: Set dynamic user details
        userName.text = "Jane Doe"
        userEmail.text = "WalletCoins - 0"

        // Open Drawer when navDrawer icon is clicked
        binding.navDrawer.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Handle navigation item clicks
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
//                R.id.nav_home -> {
//                    Toast.makeText(this, "Home Selected", Toast.LENGTH_SHORT).show()
//                }
//                R.id.nav_profile -> {
//                    Toast.makeText(this, "Profile Selected", Toast.LENGTH_SHORT).show()
//                }
//                R.id.nav_settings -> {
//                    Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show()
//                }
//                R.id.nav_logout -> {
//                    Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show()
//                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
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