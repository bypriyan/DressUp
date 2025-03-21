package com.socialseller.clothcrew.activity.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bypriyan.bustrackingsystem.utility.Constants
import com.socialseller.clothcrew.activity.profile.SetupProfileActivity
import com.socialseller.clothcrew.databinding.ActivityLoginBinding
import com.socialseller.clothcrew.databinding.ActivityOtpactivityBinding
import com.socialseller.clothcrew.utility.MyActivity
import com.socialseller.clothcrew.viewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class OTPActivity : MyActivity() {

    private val binding by lazy { ActivityOtpactivityBinding.inflate(layoutInflater) }
    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var phoneNumberValue: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Initialize UI components
        phoneNumberValue = intent.getStringExtra(Constants.KEY_USER_PHONE_NUMBER) ?: ""
        setupOtpInput()
        setupBackButton(binding.back)
        setupButtonState()

        binding.requestOTPBtn.setOnClickListener {
            val otp = getOtpString()
            authViewModel.verifyOtp("+91"+phoneNumberValue, otp)
        }

    }

    private fun setupOtpInput() {
        val otpFields = listOf(
            binding.otp1, binding.otp2, binding.otp3,
            binding.otp4, binding.otp5, binding.otp6
        )

        otpFields.forEachIndexed { index, editText ->
            val previous = otpFields.getOrNull(index - 1)
            val next = otpFields.getOrNull(index + 1)
            editText.addTextChangedListener(OtpTextWatcher(editText, previous, next))
            setOnKeyListener(editText, previous)
        }
    }

    private fun setOnKeyListener(current: EditText, previous: EditText?) {
        current.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && current.text.isEmpty()) {
                previous?.apply {
                    requestFocus()
                    text.clear()
                }
            }
            false
        }
    }

    private fun setupButtonState() {
        binding.requestOTPBtn.isEnabled = false
        binding.requestOTPBtn.alpha = 0.5f

        val otpFields = listOf(
            binding.otp1, binding.otp2, binding.otp3,
            binding.otp4, binding.otp5, binding.otp6
        )

        otpFields.forEach { editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    binding.requestOTPBtn.isEnabled = isOtpFilled()
                    binding.requestOTPBtn.alpha = if (isOtpFilled()) 1.0f else 0.5f
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }
    }

    private fun isOtpFilled(): Boolean {
        return listOf(
            binding.otp1, binding.otp2, binding.otp3,
            binding.otp4, binding.otp5, binding.otp6
        ).all { it.text?.length == 1 }
    }

    private fun getOtpString(): String {
        return listOf(
            binding.otp1.text.toString(),
            binding.otp2.text.toString(),
            binding.otp3.text.toString(),
            binding.otp4.text.toString(),
            binding.otp5.text.toString(),
            binding.otp6.text.toString()
        ).joinToString("")
    }

    inner class OtpTextWatcher(
        private val current: EditText,
        private val previous: EditText?,
        private val next: EditText?
    ) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s?.length == 1) next?.requestFocus()
            else if (s.isNullOrEmpty()) previous?.requestFocus()
        }

        override fun afterTextChanged(s: Editable?) {}
    }
}
