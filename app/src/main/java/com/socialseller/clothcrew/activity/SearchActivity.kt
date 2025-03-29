package com.socialseller.clothcrew.activity

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.adapter.AdapterProducts
import com.socialseller.clothcrew.databinding.ActivityHomeBinding
import com.socialseller.clothcrew.databinding.ActivitySearchBinding
import com.socialseller.clothcrew.databinding.FragmentSavedBinding
import com.socialseller.clothcrew.model.Item
import com.socialseller.clothcrew.utility.MyActivity

class SearchActivity : MyActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}