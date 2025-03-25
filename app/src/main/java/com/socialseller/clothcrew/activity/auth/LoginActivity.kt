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
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bypriyan.bustrackingsystem.utility.Constants
import com.bypriyan.bustrackingsystem.utility.DataStoreManager
import com.socialseller.clothcrew.activity.profile.SetupProfileActivity
import com.socialseller.clothcrew.activity.ui.HomeActivity
import com.socialseller.clothcrew.viewModel.AuthViewModel
import com.socialseller.clothcrew.databinding.ActivityLoginBinding
import com.socialseller.clothcrew.utility.KeyboardUtils
import com.socialseller.clothcrew.utility.MyActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.getValue

@AndroidEntryPoint
class LoginActivity : MyActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var splashScreen: SplashScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }
        super.onCreate(savedInstanceState)
        i++

        checkUserSession()
    }

    private fun checkUserSession() {
        authViewModel.savedUserToken.observe(this){token->
            Log.d("token", "checkUserSession: $token")
            if (token.isNullOrEmpty()) {
                setupUI()
            }else{
                authViewModel.userName.observe(this){name->
                    if(name.isNullOrEmpty()){
                        navigateToSetupProfile()
                    }else{
                        navigateToHome()
                    }
                }
            }

        }
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun navigateToSetupProfile() {
        startActivity(Intent(this, SetupProfileActivity::class.java))
        finish()
    }

    private fun setupUI() {
        splashScreen.setKeepOnScreenCondition { false }
        setContentView(binding.root) // Ensure UI is set only when needed
        setupBackButton(binding.back)
        setupPhoneNumberListener()
        setupClickListeners()
        observeOtpResponse()

        KeyboardUtils.showKeyboard(this, binding.phoneNumberET)
    }

    private fun setupPhoneNumberListener() {
        binding.phoneNumberET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val isValid = s?.length == 10
                binding.requestOTPBtn.apply {
                    isEnabled = isValid
                    alpha = if (isValid) 1.0f else 0.5f
                }

                if (isValid) {
                    KeyboardUtils.hideKeyboard(this@LoginActivity, binding.phoneNumberET)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setupClickListeners() {
        binding.requestOTPBtn.setOnClickListener { handleOtpRequest() }
    }

    private fun handleOtpRequest() {
        val phoneNumber = binding.phoneNumberET.text.toString().trim()

        if (phoneNumber.length != 10) {
            showToast("Please enter a valid mobile number")
            return
        }

        KeyboardUtils.hideKeyboard(this, binding.phoneNumberET)
        isLoading(true)
        authViewModel.sendOtp("+91$phoneNumber")
    }

    private fun observeOtpResponse() {
        authViewModel.otpResponse.observe(this) { otpResponse ->
            Log.d("OTP Response", "Response: $otpResponse")
            isLoading(false)
            otpResponse?.let {
                if (otpResponse.status == Constants.KEY_RESPONCE_SUCCESS) {
                    navigateToOtpScreen()
                } else {
                    showToast(otpResponse.message)
                }
            }
        }
    }

    private fun navigateToOtpScreen() {
        val intent = Intent(this, OTPActivity::class.java).apply {
            putExtra(Constants.KEY_USER_PHONE_NUMBER, binding.phoneNumberET.text.toString())
        }
        startActivity(intent)
    }

    private fun isLoading(isLoading: Boolean) {
        binding.progressbar.isVisible = isLoading
        binding.requestOTPBtn.isVisible = !isLoading
    }
}
