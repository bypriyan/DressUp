package com.socialseller.clothcrew.activity.cart

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.activity.PaymentScreenActivity
import com.socialseller.clothcrew.adapter.AdapterOrderCartItems
import com.socialseller.clothcrew.databinding.ActivityDeliveryInformationBinding
import com.socialseller.clothcrew.model.Item

class DeliveryInformationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeliveryInformationBinding
    val itemList = listOf(
        Item(R.drawable.category_girl_img, "Traditional"),
        Item(R.drawable.category_girl_img, "Printed"),
        Item(R.drawable.category_girl_img, "Ethnic Wear"),
        Item(R.drawable.category_girl_img, "Gown")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeliveryInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cartItemRV.adapter = AdapterOrderCartItems(this, itemList)

        binding.proceedBtn.setOnClickListener {
            startActivity(Intent(this, PaymentScreenActivity::class.java))
        }

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