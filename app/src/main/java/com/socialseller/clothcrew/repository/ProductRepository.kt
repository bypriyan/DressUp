package com.socialseller.clothcrew.repository

import android.util.Log
import com.bypriyan.bustrackingsystem.utility.Constants
import com.socialseller.clothcrew.api.ApiAuth
import com.socialseller.clothcrew.api.ApiProducts
import com.socialseller.clothcrew.api.BannerResponse
import com.socialseller.clothcrew.api.OtpResponse
import com.socialseller.clothcrew.api.OtpVerifyResponse
import com.socialseller.clothcrew.api.UserInfoResponce
import com.socialseller.clothcrew.api.UserRequest
import com.socialseller.clothcrew.api.UserResponse
import com.socialseller.clothcrew.api.collectionsResponse
import com.socialseller.clothcrew.apiResponce.ApiResponse
import com.socialseller.clothcrew.utility.HttpStatusHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Response

@Singleton
class ProductRepository @Inject constructor(private val apiProduct: ApiProducts) {

    suspend fun getCollection(): ApiResponse<collectionsResponse> {
        return safeApiCall { apiProduct.getCollections() }
    }

    suspend fun getBanners(): ApiResponse<BannerResponse> {
        return safeApiCall { apiProduct.getBanners() }
    }

    private suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): ApiResponse<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                ApiResponse.Success(response.body()!!)
            } else {
                ApiResponse.Error(HttpStatusHelper.getMessage(response.code()), null)
            }
        } catch (e: Exception) {
            ApiResponse.Error("Network Error: ${e.message}")
        }
    }

}