package com.socialseller.clothcrew.activity.stores

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.adapter.AdapyterCity
import com.socialseller.clothcrew.adapter.AdapyterImageCards
import com.socialseller.clothcrew.databinding.ActivityStoreLocationBinding
import com.socialseller.clothcrew.model.Item

class StoreLocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStoreLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityStoreLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemList = listOf(
            Item(R.drawable.category_girl_img, "Traditional"),
            Item(R.drawable.category_girl_img, "Printed"),
            Item(R.drawable.category_girl_img, "Ethnic Wear"),
            Item(R.drawable.category_girl_img, "Gown")
        )
        var adaptercategory = AdapyterCity(this, itemList)
        binding.cityRv.adapter = adaptercategory
        binding.cardsRv.adapter = AdapyterImageCards(this, itemList)

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