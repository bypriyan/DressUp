package com.socialseller.clothcrew.activity.address

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.databinding.ActivityAddressBinding
import com.socialseller.clothcrew.databinding.ActivityProfileBinding

class AddressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addAddress.setOnClickListener {
            startActivity(Intent(this, AddAddressActivity::class.java))
        }

    }
}