package com.socialseller.clothcrew.api

import com.socialseller.clothcrew.apiResponce.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiAuth {

    @FormUrlEncoded
    @POST("users/otp/send")
    suspend fun sendOTP(
        @Field("phone") userId: String
    ): Response<OtpResponse>

    @FormUrlEncoded
    @POST("custom/user/verify")
    suspend fun verifyOTP(
        @Field("phoneNumber") phoneNumber: String,
        @Field("otp") otp: String
    ): Response<OtpVerifyResponse>

    @POST("auth/local/register")
    suspend fun registerUser(@Body userRequest: UserRequest): Response<UserResponse>

    @GET("users/me?populate=avatar")
    suspend fun getUserInfo(@Header("Authorization") token: String): Response<UserResponse>

}

// Response data class
data class OtpResponse(
    val status: String,
    val message: String
)

data class OtpVerifyResponse(
    val jwt: String,
    val message: String,
    val status: String,
    val user: User?
)

data class User(
    val avatar: Avatar?,  // Change this from String? to Avatar?
    val countryCode: String,
    val id: Int,
    val name: String?,
    val otp: String,
    val otp_expiration: String,
    val phone: String
)

// Define a separate data class for avatar
data class Avatar(
    val url: String
)

//profile create
data class UserRequest(
    val email: String?=null,
    val username: String?=null,
    val phone: String,
    val name: String
)

data class UserResponse(
    val email: String?,
    val username: String?,
    val phone: String?,
    val name: String?
)

data class ErrorResponse(
    val message: String
)

data class UserResponce(
    val id: String?,
    val username: String?,
    val email: String?,
    val provider: String?,
    val confirmed: String?,
    val blocked: String?,
    val isPremium: String?,
    val phone: String?,
    val isAdmin: String?,
    val wallet_balance: String?,
    val countryCode: String?,
    val name: String?,
    val fcmToken: String?,
    val businessName: String?,
    val ordersCount: String?,
    val profit: String?,
    val shares: String?,
    val otp: String?,
    val otp_expiration: String?,
    val personal_id: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val nick_name: String?,
    val avatar: String?
)

