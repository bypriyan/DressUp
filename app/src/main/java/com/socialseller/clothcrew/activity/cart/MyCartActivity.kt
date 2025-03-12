package com.socialseller.clothcrew.activity.cart

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.adapter.AdapterAddress
import com.socialseller.clothcrew.adapter.AdapterOrderCartItems
import com.socialseller.clothcrew.databinding.ActivityMyCartBinding
import com.socialseller.clothcrew.databinding.ActivityStoreLocationBinding
import com.socialseller.clothcrew.model.Item

class MyCartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyCartBinding
    val itemList = listOf(
        Item(R.drawable.category_girl_img, "Traditional"),
        Item(R.drawable.category_girl_img, "Printed"),
        Item(R.drawable.category_girl_img, "Ethnic Wear"),
        Item(R.drawable.category_girl_img, "Gown")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cartItemRV.adapter = AdapterOrderCartItems(this, itemList)

        binding.proceedBtn.setOnClickListener {
            startActivity(Intent(this, DeliveryInformationActivity::class.java))
        }

    }
}