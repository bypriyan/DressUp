package com.socialseller.clothcrew.activity.profile

import android.content.Intent
import android.os.Bundle
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


    }
}