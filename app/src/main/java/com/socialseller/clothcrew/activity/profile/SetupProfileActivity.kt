package com.socialseller.clothcrew.activity.profile

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.socialseller.clothcrew.activity.BodyMeasureActivity
import com.socialseller.clothcrew.databinding.ActivitySetupProfileBinding

class SetupProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySetupProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetupProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.requestOTPBtn.setOnClickListener {
            val intent = Intent(this, BodyMeasureActivity::class.java)
            startActivity(intent)
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