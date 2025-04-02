package com.socialseller.clothcrew.repository

import android.util.Log
import com.bypriyan.bustrackingsystem.utility.Constants
import com.socialseller.clothcrew.api.ApiAddress
import com.socialseller.clothcrew.api.ApiAuth
import com.socialseller.clothcrew.api.ApiProducts
import com.socialseller.clothcrew.api.OtpResponse
import com.socialseller.clothcrew.api.OtpVerifyResponse
import com.socialseller.clothcrew.api.UserInfoResponce
import com.socialseller.clothcrew.api.UserRequest
import com.socialseller.clothcrew.api.UserResponse
import com.socialseller.clothcrew.apiResponce.ApiResponse
import com.socialseller.clothcrew.apiResponce.SearchProductApiResponce
import com.socialseller.clothcrew.modelResponce.BannerResponse
import com.socialseller.clothcrew.modelResponce.CategoriesResponse
import com.socialseller.clothcrew.modelResponce.CategoryProductResponce
import com.socialseller.clothcrew.modelResponce.ProductDetailsApiResponceDT
import com.socialseller.clothcrew.modelResponce.SellerStoreResponce
import com.socialseller.clothcrew.modelResponce.collectionsResponse
import com.socialseller.clothcrew.models.address.post.AddressAddReq
import com.socialseller.clothcrew.models.address.post.AddressAddResponse
import com.socialseller.clothcrew.utility.HttpStatusHelper
import com.socialseller.clothcrew.utility.ResponceHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Response
import kotlin.String

@Singleton
class AddressRepository @Inject constructor(private val apiAddress: ApiAddress) {

    suspend fun addAddress(token: String,address: AddressAddReq): ApiResponse<AddressAddResponse> {
        return ResponceHelper.safeApiCall { apiAddress.addAddresses(token, address)}
    }

}