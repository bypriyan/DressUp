package com.socialseller.clothcrew.activity.stores

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.adapter.AdapterStore
import com.socialseller.clothcrew.adapter.AdapyterCity
import com.socialseller.clothcrew.adapter.AdapyterImageCards
import com.socialseller.clothcrew.databinding.ActivityStoreLocationBinding
import com.socialseller.clothcrew.databinding.ActivityStoresByCityBinding
import com.socialseller.clothcrew.model.Item

class StoresByCityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStoresByCityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoresByCityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemList = listOf(
            Item(R.drawable.category_girl_img, "Traditional"),
            Item(R.drawable.category_girl_img, "Printed"),
            Item(R.drawable.category_girl_img, "Ethnic Wear"),
            Item(R.drawable.category_girl_img, "Gown")
        )
//        var adapterStore = AdapterStore(this, itemList)
//        binding.storeRv.adapter = adapterStore

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