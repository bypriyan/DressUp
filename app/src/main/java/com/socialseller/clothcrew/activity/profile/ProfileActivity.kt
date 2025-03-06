package com.socialseller.clothcrew.activity.profile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.activity.address.AddressActivity
import com.socialseller.clothcrew.databinding.ActivityHomeBinding
import com.socialseller.clothcrew.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.profile.setOnClickListener {
            startActivity(Intent(this, EditProfileActivity::class.java))
        }

        binding.address.setOnClickListener {
            startActivity(Intent(this, AddressActivity::class.java))
        }



    }
}