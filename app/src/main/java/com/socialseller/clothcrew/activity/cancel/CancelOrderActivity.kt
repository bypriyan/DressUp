package com.socialseller.clothcrew.activity.cancel

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.databinding.ActivityCancelOrderBinding

class CancelOrderActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCancelOrderBinding

    val reasons = listOf(
        "Price was too high",
        "Changed my mind",
        "Too long to pack and ship",
        "Did not like the item",
        "Other (Please Specify)"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCancelOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var adapter  = ArrayAdapter(this, R.layout.drop_down_items, reasons)

        binding.autoCompleteTextViewClass.setAdapter(adapter)
        // Handle click event to show dropdown
        binding.autoCompleteTextViewClass.setOnClickListener {
            binding.autoCompleteTextViewClass.showDropDown()
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