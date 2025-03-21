package com.socialseller.clothcrew.repository

import android.util.Log
import com.socialseller.clothcrew.api.ApiAuth
import com.socialseller.clothcrew.api.OtpResponse
import com.socialseller.clothcrew.api.OtpVerifyResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(private val apiAuth: ApiAuth) {

    suspend fun sendOtp(phone: String): OtpResponse {
        return try {
            val response = apiAuth.sendOTP(phone)
            if (response.isSuccessful) {
                response.body()!!
            } else {
                OtpResponse("error", "Failed to send OTP")
            }
        } catch (e: Exception) {
            OtpResponse("error", "Network error: ${e.message}")
        }
    }

    suspend fun verifyOtp(phone: String, otp: String): OtpVerifyResponse {
        return try {
            val response = apiAuth.verifyOTP(phone, otp)
            if (response.isSuccessful) {
                response.body()!!
            } else {
                OtpVerifyResponse("error", response.message(), response.code(), null)
            }
        } catch (e: Exception) {
            OtpVerifyResponse("error", "Network error: ${e.message}")
        }
    }

}