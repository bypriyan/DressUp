package com.socialseller.clothcrew.activity.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.widget.EditText
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bypriyan.bustrackingsystem.utility.Constants
import com.bypriyan.bustrackingsystem.utility.DataStoreManager
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.activity.profile.SetupProfileActivity
import com.socialseller.clothcrew.activity.ui.HomeActivity
import com.socialseller.clothcrew.api.OtpVerifyResponse
import com.socialseller.clothcrew.api.User
import com.socialseller.clothcrew.databinding.ActivityOtpactivityBinding
import com.socialseller.clothcrew.utility.MyActivity
import com.socialseller.clothcrew.viewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.getValue

@AndroidEntryPoint
class OTPActivity : MyActivity() {

    private val binding by lazy { ActivityOtpactivityBinding.inflate(layoutInflater) }
    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var phoneNumberValue: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        phoneNumberValue = intent.getStringExtra(Constants.KEY_USER_PHONE_NUMBER).orEmpty()
        binding.otpMessageTv.text = "6 digit OTP sent to +91$phoneNumberValue"

        setupOtpInput()
        setupBackButton(binding.back)
        setupButtonState()

        binding.verifyOTPBtn.setOnClickListener {
            val otp = getOtpString()
            isLoading(true)
            authViewModel.verifyOtp("+91$phoneNumberValue", otp)
        }

        observeOtpResponse()
    }

    private fun observeOtpResponse() {
        // Observe the verifyOtpResponse LiveData
        authViewModel.verifyOtpResponse.observe(this) { otpResponse ->
            otpResponse?.let {
                Log.d("OTP Response", "Response: $otpResponse")
                isLoading(false)
                handleOtpResponse(it)
            }
        }
    }

    private fun handleOtpResponse(otpResponse: OtpVerifyResponse) {
        when (otpResponse.status) {
            Constants.KEY_RESPONCE_SUCCESS -> {
                if (otpResponse.user?.name.isNullOrEmpty()) {
                    navigateToSetupProfile()
                } else {
                    navigateToHomeActivity()
                }
                finish()
            }
            else -> showToast(otpResponse.message)
        }
    }

    private fun navigateToHomeActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    private fun navigateToSetupProfile() {
        startActivity(Intent(this, SetupProfileActivity::class.java))
    }

    private fun setupOtpInput() {
        val otpFields = listOf(binding.otp1, binding.otp2, binding.otp3, binding.otp4, binding.otp5, binding.otp6)
        otpFields.forEachIndexed { index, editText ->
            val previous = otpFields.getOrNull(index - 1)
            val next = otpFields.getOrNull(index + 1)

            editText.addTextChangedListener(OtpTextWatcher(editText, previous, next))

            editText.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && editText.text.isEmpty()) {
                    previous?.apply {
                        requestFocus()
                        text.clear()
                    }
                }
                false
            }
        }
    }

    private fun setupButtonState() {
        val otpFields = listOf(binding.otp1, binding.otp2, binding.otp3, binding.otp4, binding.otp5, binding.otp6)
        otpFields.forEach { it.addTextChangedListener(OtpTextWatcher(it, null, null)) }
    }

    private fun getOtpString(): String =
        listOf(binding.otp1, binding.otp2, binding.otp3, binding.otp4, binding.otp5, binding.otp6)
            .joinToString("") { it.text.toString() }

    inner class OtpTextWatcher(
        private val current: EditText,
        private val previous: EditText?,
        private val next: EditText?
    ) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            when {
                s?.length == 1 -> next?.requestFocus()
                s.isNullOrEmpty() -> previous?.requestFocus()
            }
        }
        override fun afterTextChanged(s: Editable?) {
            updateButtonState()
        }
    }

    private fun updateButtonState() {
        val isFilled = listOf(binding.otp1, binding.otp2, binding.otp3, binding.otp4, binding.otp5, binding.otp6)
            .all { it.text?.length == 1 }

        binding.verifyOTPBtn.apply {
            isEnabled = isFilled
            alpha = if (isFilled) 1.0f else 0.5f
        }
    }

    private fun isLoading(isLoading: Boolean) {
        binding.progressbar.isVisible = isLoading
        binding.verifyOTPBtn.isVisible = !isLoading
    }
}