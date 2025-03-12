package com.socialseller.clothcrew.activity.address

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.adapter.AdapterAddress
import com.socialseller.clothcrew.adapter.AdapterProducts
import com.socialseller.clothcrew.databinding.ActivityAddressBinding
import com.socialseller.clothcrew.databinding.ActivityProfileBinding
import com.socialseller.clothcrew.model.Item

class AddressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddressBinding
    val itemList = listOf(
        Item(R.drawable.category_girl_img, "Traditional"),
        Item(R.drawable.category_girl_img, "Printed"),
        Item(R.drawable.category_girl_img, "Ethnic Wear"),
        Item(R.drawable.category_girl_img, "Gown")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addAddress.setOnClickListener {
            startActivity(Intent(this, AddAddressActivity::class.java))
        }

        binding.addreddRv.adapter = AdapterAddress(this, itemList)

    }
}