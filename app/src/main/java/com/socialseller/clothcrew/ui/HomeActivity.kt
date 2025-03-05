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
    private lateinit var headerView: View

    private lateinit var recyclerView: RecyclerView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize DrawerLayout
        drawerLayout = findViewById(R.id.drawerLayout)
        recyclerView = findViewById(R.id.navRecyclerView)

        // Open Drawer when navDrawer icon is clicked
        binding.navDrawer.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }



        // Menu Items with Icons
        val menuItems = listOf(
            NavMenuItem("Home", R.drawable.banner_girl),
            NavMenuItem("My Profile", R.drawable.banner_girl),
            NavMenuItem("Order History", R.drawable.banner_girl),
            NavMenuItem("Shop By Category", R.drawable.banner_girl),
            NavMenuItem("Shop For Men", R.drawable.banner_girl),
            NavMenuItem("Shop For Women", R.drawable.banner_girl),
            NavMenuItem("Shop For Kids", R.drawable.banner_girl),
            NavMenuItem("Store Location", R.drawable.banner_girl),
            NavMenuItem("Logout", R.drawable.banner_girl)
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NavDrawerAdapter(menuItems) { menuItem ->
            handleNavItemClick(menuItem)
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
    }

    private fun handleNavItemClick(item: NavMenuItem) {
        when (item.title) {
            "Home" -> Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show()
            "Logout" -> Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
    }
}