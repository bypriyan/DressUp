package com.socialseller.clothcrew.activity.orders

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.adapter.AdapterOrderItems
import com.socialseller.clothcrew.adapter.AdapterStore
import com.socialseller.clothcrew.databinding.ActivityOrderDetailsBinding
import com.socialseller.clothcrew.databinding.ActivityStoresByCityBinding
import com.socialseller.clothcrew.model.Item

class OrderDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemList = listOf(
            Item(R.drawable.category_girl_img, "Traditional"),
            Item(R.drawable.category_girl_img, "Printed"),
            Item(R.drawable.category_girl_img, "Ethnic Wear"),
            Item(R.drawable.category_girl_img, "Gown")
        )
        binding.orderedItemsRv.adapter = AdapterOrderItems(this, itemList)

    }
}