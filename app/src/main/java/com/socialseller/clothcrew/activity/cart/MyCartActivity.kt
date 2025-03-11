package com.socialseller.clothcrew.activity.cart

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.databinding.ActivityMyCartBinding
import com.socialseller.clothcrew.databinding.ActivityStoreLocationBinding

class MyCartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}