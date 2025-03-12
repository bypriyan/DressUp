package com.socialseller.clothcrew.activity.orders

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.activity.cart.DeliveryInformationActivity
import com.socialseller.clothcrew.adapter.AdapterOrderCartItems
import com.socialseller.clothcrew.databinding.ActivityMyCartBinding
import com.socialseller.clothcrew.databinding.ActivityOrderConfirmationBinding
import com.socialseller.clothcrew.databinding.ActivityOrderDetailsBinding
import com.socialseller.clothcrew.databinding.ActivityPayOnDeliveryBinding
import com.socialseller.clothcrew.model.Item

class OrderConfirmationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}