package com.socialseller.clothcrew.activity.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.socialseller.clothcrew.activity.profile.SetupProfileActivity
import com.socialseller.clothcrew.databinding.ActivityOtpactivityBinding

class OTPActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupOtpInput()

        binding.requestOTPBtn.setOnClickListener {
            if (areAllEditTextsFilled())
                startActivity(Intent(this, SetupProfileActivity::class.java))
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

    private fun setupOtpInput() {
        binding.otp1.addTextChangedListener(OtpTextWatcher(binding.otp1, null, binding.otp2))
        binding.otp2.addTextChangedListener(OtpTextWatcher(binding.otp2, binding.otp1, binding.otp3))
        binding.otp3.addTextChangedListener(OtpTextWatcher(binding.otp3, binding.otp2, binding.otp4))
        binding.otp4.addTextChangedListener(OtpTextWatcher(binding.otp4, binding.otp3, binding.otp5))
        binding.otp5.addTextChangedListener(OtpTextWatcher(binding.otp5, binding.otp4, binding.otp6))
        binding.otp6.addTextChangedListener(OtpTextWatcher(binding.otp6, binding.otp5, null))

        setOnKeyListener(binding.otp1, null, binding.otp2)
        setOnKeyListener(binding.otp2, binding.otp1, binding.otp3)
        setOnKeyListener(binding.otp3, binding.otp2, binding.otp4)
        setOnKeyListener(binding.otp4, binding.otp3, binding.otp5)
        setOnKeyListener(binding.otp5, binding.otp4, binding.otp6)
        setOnKeyListener(binding.otp6, binding.otp5, null)
    }

    private fun setOnKeyListener(current: EditText, previous: EditText?, next: EditText?) {
        current.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                if (current.text.isEmpty() && previous != null) {
                    previous.requestFocus()
                    previous.text.clear()
                }
            }
            false
        }
    }

    private fun areAllEditTextsFilled(): Boolean {
        return listOf(
            binding.otp1,
            binding.otp2,
            binding.otp3,
            binding.otp4,
            binding.otp5,
            binding.otp6
        ).all { it.text?.isNotEmpty() == true }
    }

    inner class OtpTextWatcher(
        private val current: EditText,
        private val previous: EditText?,
        private val next: EditText?
    ) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s?.length == 1) {
                // Move focus to the next EditText when a character is entered
                next?.requestFocus()
            } else if (s.isNullOrEmpty() && previous != null) {
                // Move focus to the previous EditText when a character is deleted
                previous.requestFocus()
            }
        }

        override fun afterTextChanged(s: Editable?) {
            // Ensure that if a number is entered in a previous EditText, focus moves to the next EditText
            if (!s.isNullOrEmpty() && s.length == 1 && next != null) {
                next.requestFocus()
            }
        }
    }
}