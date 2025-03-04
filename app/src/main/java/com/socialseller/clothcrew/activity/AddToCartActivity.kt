package com.socialseller.clothcrew.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.adapter.SizeAdapter
import com.socialseller.clothcrew.databinding.ActivityAddToCartBinding
import com.socialseller.clothcrew.databinding.ActivitySetupProfileBinding

class AddToCartActivity : AppCompatActivity() {

    private lateinit var sizeAdapter: SizeAdapter
    private val sizes = listOf("S", "L", "XL", "XXL", "FREE SIZE")
    private lateinit var binding : ActivityAddToCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddToCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        sizeAdapter = SizeAdapter(sizes) { selectedSize ->
            Toast.makeText(this, "Selected: $selectedSize", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = sizeAdapter

    }
}