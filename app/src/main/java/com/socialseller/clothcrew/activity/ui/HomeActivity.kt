package com.socialseller.clothcrew.activity.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.bypriyan.bustrackingsystem.utility.Constants
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.activity.auth.LoginActivity
import com.socialseller.clothcrew.activity.stores.StoreLocationActivity
import com.socialseller.clothcrew.activity.profile.ProfileActivity
import com.socialseller.clothcrew.apiResponce.ApiResponse
import com.socialseller.clothcrew.databinding.ActivityHomeBinding
import com.socialseller.clothcrew.utility.MyActivity
import com.socialseller.clothcrew.viewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class HomeActivity : MyActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    lateinit var bottomNavigationView: BottomNavigationView
    private val authViewModel: AuthViewModel by viewModels()

    private var isFabExpanded = false // State to track expansion


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, R.id.frameLayout);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        loadToken()
        obsurveUserInfoResponce()


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

        // Set Main FAB Click Listener
        binding.fab.setOnClickListener {
            if (isFabExpanded) {
                binding.fab.setImageDrawable(getDrawable(R.drawable.call))
                collapseFABs()
            } else {
                binding.fab.setImageDrawable(getDrawable(R.drawable.close_icon))
                expandFABs()
            }
        }
    }

    private fun obsurveUserInfoResponce() {
        // Observe response with repeatOnLifecycle (instead of launchWhenStarted)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                authViewModel.userInfo.collect { message ->
                    if(message!= Constants.KEY_RESPONCE_SUCCESS){
                        message?.let {
                            showToast(message)
                            clearDataStore()
                            navigateToLoginScreen()
                        }
                    }
                }
            }
        }

    }

    private fun navigateToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun loadToken() {
        lifecycleScope.launch {
            authViewModel.savedUserToken.observe(this@HomeActivity) { token ->
                token?.let {
                    authViewModel.fetchUserInfo(token)
                }
            }
        }
    }

    private fun expandFABs() {
        // Set FABs to visible before animation starts
        binding.callFab.visibility = View.VISIBLE
        binding.whatsappFab.visibility = View.VISIBLE

        // Animate FABs to move up and fade in
        binding.callFab.animate()
            .translationY(0f)
            .alpha(1f)
            .setInterpolator(OvershootInterpolator())
            .setDuration(400)
            .setListener(null) // Reset listener to avoid conflict
            .start()

        binding.whatsappFab.animate()
            .translationY(0f)
            .alpha(1f)
            .setInterpolator(OvershootInterpolator())
            .setDuration(400)
            .setListener(null) // Reset listener to avoid conflict
            .start()

        isFabExpanded = true
    }

    private fun collapseFABs() {
        // Animate FABs to move down and fade out
        binding.callFab.animate()
            .translationY(100f)
            .alpha(0f)
            .setInterpolator(OvershootInterpolator())
            .setDuration(400)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.callFab.visibility = View.INVISIBLE
                }
            })
            .start()

        binding.whatsappFab.animate()
            .translationY(100f)
            .alpha(0f)
            .setInterpolator(OvershootInterpolator())
            .setDuration(400)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.whatsappFab.visibility = View.INVISIBLE
                }
            })
            .start()

        isFabExpanded = false
    }

}