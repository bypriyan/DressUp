package com.socialseller.clothcrew.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.socialseller.clothcrew.api.OtpResponse
import com.socialseller.clothcrew.api.OtpVerifyResponse
import com.socialseller.clothcrew.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _otpResponse = MutableLiveData<OtpResponse>()
    val otpResponse: LiveData<OtpResponse> = _otpResponse

    private val _verifyOtpResponse = MutableLiveData<OtpVerifyResponse>()
    val verifyOtpResponse: LiveData<OtpVerifyResponse> = _verifyOtpResponse

    fun sendOtp(phone: String) {
        viewModelScope.launch {
            _otpResponse.value = authRepository.sendOtp(phone)
        }
    }

    fun verifyOtp(phoneNumber: String, otp: String) {
        viewModelScope.launch {
            _verifyOtpResponse.value = authRepository.verifyOtp(phoneNumber, otp)
        }
    }
}