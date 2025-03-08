package com.socialseller.clothcrew.activity

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.socialseller.clothcrew.databinding.ActivityBodyMeasureBinding
import com.socialseller.clothcrew.activity.ui.HomeActivity

class BodyMeasureActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBodyMeasureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBodyMeasureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val armholeValues = arrayOf("42", "43", "44") // Example values
        val shoulderValues = arrayOf("19", "20", "21") // Example values

        val armholeAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, armholeValues)
        val shoulderAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, shoulderValues)

        binding.armholeSpinner.adapter = armholeAdapter
        binding.shoulderSpinner.adapter = shoulderAdapter

        binding.FinishBtn.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}