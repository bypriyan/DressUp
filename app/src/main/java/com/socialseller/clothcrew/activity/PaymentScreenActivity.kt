package com.socialseller.clothcrew.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.databinding.ActivityMyCartBinding
import com.socialseller.clothcrew.databinding.ActivityPaymentScreenBinding

class PaymentScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.payOnDelivery.setOnClickListener {
            startActivity(Intent(this, PayOnDeliveryActivity::class.java))
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