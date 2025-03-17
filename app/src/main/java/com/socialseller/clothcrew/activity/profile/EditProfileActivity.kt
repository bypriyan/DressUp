package com.socialseller.clothcrew.activity.profile

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.databinding.ActivityEditProfileBinding
import com.socialseller.clothcrew.databinding.ActivityOrderDetailsBinding

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
        //back pressed
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })

    }
}