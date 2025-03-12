package com.socialseller.clothcrew.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.activity.cart.MyCartActivity
import com.socialseller.clothcrew.adapter.AdapterAddress
import com.socialseller.clothcrew.adapter.AdapterBodySize
import com.socialseller.clothcrew.adapter.AdapterProductHorizontal
import com.socialseller.clothcrew.adapter.AdapterProductImages
import com.socialseller.clothcrew.adapter.SizeAdapter
import com.socialseller.clothcrew.databinding.ActivityAddressBinding
import com.socialseller.clothcrew.databinding.ActivityProductDetailsBinding
import com.socialseller.clothcrew.model.Item

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    val itemList = listOf(
        Item(R.drawable.category_girl_img, "Traditional"),
        Item(R.drawable.category_girl_img, "Printed"),
        Item(R.drawable.category_girl_img, "Ethnic Wear"),
        Item(R.drawable.category_girl_img, "Gown")
    )

    private lateinit var sizeAdapter: SizeAdapter
    private val sizes = listOf("S", "L", "XL", "XXL", "FREE SIZE")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageRV.adapter = AdapterProductImages(this, itemList)
        binding.partSizeRvRV.adapter = AdapterBodySize(this, itemList)
        binding.relatedProductRv.adapter = AdapterProductHorizontal(this, itemList)
        //size
        val recyclerView: RecyclerView = findViewById(R.id.sizeRV)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        sizeAdapter = SizeAdapter(sizes) { selectedSize ->
            Toast.makeText(this, "Selected: $selectedSize", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = sizeAdapter

        binding.addToCartTv.setOnClickListener {
            startActivity(Intent(this, MyCartActivity::class.java))
        }

        binding.continueBtn.setOnClickListener {
            startActivity(Intent(this, MyCartActivity::class.java))
        }

    }
}