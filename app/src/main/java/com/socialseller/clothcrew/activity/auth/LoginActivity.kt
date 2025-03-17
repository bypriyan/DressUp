package com.socialseller.clothcrew.activity.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.socialseller.clothcrew.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.requestOTPBtn.setOnClickListener {
            if(binding.editTextMobileNumber.text.toString().isEmpty() || binding.editTextMobileNumber.text.toString().length!=10){
                Toast.makeText(this, "Please enter valid mobile number", Toast.LENGTH_SHORT).show()
            }else{
                startActivity(Intent(this, OTPActivity::class.java))
            }

        }

        binding.editTextMobileNumber.setText("9876543210")

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