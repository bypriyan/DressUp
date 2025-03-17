package com.socialseller.clothcrew.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.activity.orders.OrderConfirmationActivity
import com.socialseller.clothcrew.databinding.ActivityPayOnDeliveryBinding
import com.socialseller.clothcrew.databinding.ActivityPaymentScreenBinding

class PayOnDeliveryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPayOnDeliveryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayOnDeliveryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.proceedBtn.setOnClickListener {
            startActivity(Intent(this, OrderConfirmationActivity::class.java))
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