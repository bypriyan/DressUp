package com.socialseller.clothcrew.activity.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bypriyan.bustrackingsystem.utility.Constants
import com.socialseller.clothcrew.viewModel.AuthViewModel
import com.socialseller.clothcrew.databinding.ActivityLoginBinding
import com.socialseller.clothcrew.utility.KeyboardUtils
import com.socialseller.clothcrew.utility.MyActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class LoginActivity : MyActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupBackButton(binding.back)
        observeOtpResponse()
        setupPhoneNumberListener()
        setupClickListeners()

        KeyboardUtils.showKeyboard(this, binding.phoneNumberET)
    }

    private fun setupPhoneNumberListener() {
        binding.phoneNumberET.addTextChangedListener(phoneNumberTextWatcher)
    }

    private val phoneNumberTextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            val isValid = s?.length == 10
            binding.requestOTPBtn.isEnabled = isValid
            binding.requestOTPBtn.alpha = if (isValid) 1.0f else 0.5f

            if (isValid) {
                KeyboardUtils.hideKeyboard(this@LoginActivity, binding.phoneNumberET)
            }
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    private fun setupClickListeners() {
        binding.requestOTPBtn.setOnClickListener {
            handleOtpRequest()
        }
    }

    private fun handleOtpRequest() {
        val phoneNumber = binding.phoneNumberET.text.toString()

        if (phoneNumber.length != 10) {
            showToast("Please enter a valid mobile number")
            return
        }

        KeyboardUtils.hideKeyboard(this, binding.phoneNumberET)
        isLoading(true)
        authViewModel.sendOtp("+91"+phoneNumber)
    }

    private fun observeOtpResponse() {
        authViewModel.otpResponse.observe(this) { otpResponse ->
            isLoading(false)
            if (otpResponse.status == Constants.KEY_RESPONCE_SUCCESS) {
                var intent  = Intent(this, OTPActivity::class.java).apply {
                    putExtra(Constants.KEY_USER_PHONE_NUMBER, binding.phoneNumberET.text.toString())
                }
                startActivity(intent)
            } else {
                showToast(otpResponse.message)
            }
        }
    }

    private fun isLoading(isLoading: Boolean) {
        binding.progressbar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.requestOTPBtn.visibility = if (isLoading) View.GONE else View.VISIBLE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
