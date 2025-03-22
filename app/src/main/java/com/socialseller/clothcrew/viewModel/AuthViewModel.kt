package com.socialseller.clothcrew.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bypriyan.bustrackingsystem.utility.Constants
import com.bypriyan.bustrackingsystem.utility.DataStoreManager
import com.google.gson.Gson
import com.socialseller.clothcrew.api.ErrorResponse
import com.socialseller.clothcrew.api.OtpResponse
import com.socialseller.clothcrew.api.OtpVerifyResponse
import com.socialseller.clothcrew.api.User
import com.socialseller.clothcrew.api.UserRequest
import com.socialseller.clothcrew.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    private val _otpResponse = MutableLiveData<OtpResponse?>()
    val otpResponse: LiveData<OtpResponse?> get() = _otpResponse

    private val _verifyOtpResponse = MutableLiveData<OtpVerifyResponse?>()
    val verifyOtpResponse: LiveData<OtpVerifyResponse?> get() = _verifyOtpResponse

    private val _savedUserToken = MutableLiveData<String?>()
    val savedUserToken: LiveData<String?> get() = _savedUserToken

    private val _userName = MutableLiveData<String?>()
    val userName: LiveData<String?> get() = _userName

    private val _registerRresponse = MutableLiveData<String>()
    val registerRresponse: LiveData<String> get() = _registerRresponse

    init {
        // Load saved user token and username when ViewModel is created
        viewModelScope.launch {
            dataStoreManager.getString(Constants.KEY_USER_TOKEN).collect { token ->
                _savedUserToken.value = token
            }
        }

        viewModelScope.launch {
            dataStoreManager.getString(Constants.KEY_USER_NAME).collect { name ->
                _userName.value = name
            }
        }
    }

    fun sendOtp(phone: String) = viewModelScope.launch {
        try {
            val response = authRepository.sendOtp(phone)
            _otpResponse.value = response
        } catch (e: Exception) {
            Log.e("AuthViewModel", "OTP Sending Failed: ${e.message}")
        }
    }

    fun verifyOtp(phoneNumber: String, otp: String) = viewModelScope.launch {
        try {
            val response = authRepository.verifyOtp(phoneNumber, otp)
            _verifyOtpResponse.value = response
            response.jwt?.let { saveUserToken(it) }
            response.user?.let { saveUserDetails(it) }
        } catch (e: Exception) {
            Log.e("AuthViewModel", "OTP Verification Failed: ${e.message}")
        }
    }

    private fun saveUserToken(token: String) = viewModelScope.launch {
        Log.d("modelsave", "saveUserToken: $token")
        dataStoreManager.putString(Constants.KEY_USER_TOKEN, token)
        _savedUserToken.value = token
    }

    private fun saveUserDetails(user: User) = viewModelScope.launch {
        Log.d("modelsave", "saveUserToken: $user")
        dataStoreManager.putString(Constants.KEY_USER_ID, user.id.toString())
        user.name?.let { dataStoreManager.putString(Constants.KEY_USER_NAME, it) }
        dataStoreManager.putString(Constants.KEY_USER_COUNTRY_CODE, user.countryCode)
        user.avatar?.url?.let { dataStoreManager.putString(Constants.KEY_USER_AVATAR, it) }
        dataStoreManager.putString(Constants.KEY_USER_PHONE_NUMBER, user.phone)
        // Update LiveData for username
        _userName.value = user.name
    }

    fun registerUser(userRequest: UserRequest) {
        viewModelScope.launch {
            try {
                val result = authRepository.registerUser(userRequest)
                if (result.isSuccessful) {
                    _registerRresponse.postValue("Registration Successful: ${result.body()?.name}")
                } else {
                    val errorMessage = parseError(result)
                    _registerRresponse.postValue("Error: $errorMessage")
                }
            } catch (e: IOException) {
                _registerRresponse.postValue("Network Error: ${e.message}")
            } catch (e: HttpException) {
                _registerRresponse.postValue("Server Error: ${e.message()}")
            }
        }
    }

    private fun parseError(response: Response<*>): String {
        return try {
            val errorBody = response.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
            errorResponse.message
        } catch (e: Exception) {
            "Unknown Error"
        }
    }
}