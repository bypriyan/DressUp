package com.socialseller.clothcrew.activity.cancel

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.databinding.ActivityCancelConfirmationBinding
import com.socialseller.clothcrew.databinding.ActivityLoginBinding

class CancelConfirmationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCancelConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCancelConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

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