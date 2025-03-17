package com.socialseller.clothcrew.activity

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.databinding.ActivityCategoryStoreBinding
import com.socialseller.clothcrew.databinding.ActivityOrderDetailsBinding

class CategoryStoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryStoreBinding.inflate(layoutInflater)
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