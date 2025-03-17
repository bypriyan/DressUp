package com.socialseller.clothcrew.activity.stores

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bypriyan.sharemarketcourseinhindi.adapter.AdapterOnBordingScreen
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.adapter.AdapterProducts
import com.socialseller.clothcrew.adapter.AdapyterBigCategory
import com.socialseller.clothcrew.adapter.AdapyterCategory
import com.socialseller.clothcrew.databinding.ActivityOrderDetailsBinding
import com.socialseller.clothcrew.databinding.ActivityStorePageBinding
import com.socialseller.clothcrew.model.Item

class StorePageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStorePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStorePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemList = listOf(
            Item(R.drawable.category_girl_img, "Traditional"),
            Item(R.drawable.category_girl_img, "Printed"),
            Item(R.drawable.category_girl_img, "Ethnic Wear"),
            Item(R.drawable.category_girl_img, "Gown")
        )
        val adapterBig = AdapyterBigCategory(this,itemList)
        val adapterProducts = AdapterProducts(this, itemList)
        binding.rvCategory.adapter = AdapyterCategory(this,itemList)
        binding.bigCategoriesRV.adapter = adapterBig
        binding.trandingRv.adapter = adapterProducts
        binding.festBigCategoryBig.adapter = adapterBig
        binding.bestSellerRv.adapter = adapterProducts

        //view pager
        var adapter = AdapterOnBordingScreen(this, getListOfOnBordingScreenContent())
        binding.viewPager2.adapter = adapter
        binding.wormDotsIndicator.attachTo(binding.viewPager2)
        binding.viewPager2.isUserInputEnabled = true

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

    fun getListOfOnBordingScreenContent():List<Int>{
        return listOf(
            R.drawable.banner_two,
            R.drawable.banner_one,
            R.drawable.banner_three,
            R.drawable.banner_zero
        )
    }
}