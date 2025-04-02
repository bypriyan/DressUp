package com.socialseller.clothcrew.api

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.bypriyan.bustrackingsystem.utility.Constants
import com.socialseller.clothcrew.apiResponce.ApiResponse
import com.socialseller.clothcrew.models.address.post.AddressAddReq
import com.socialseller.clothcrew.models.address.post.AddressAddResponse
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiAddress {

    @POST("custom/addAddress")
    suspend fun addAddresses(
        @Header("Authorization") token: String,
        @Body addressAdd: AddressAddReq
    ): Response<AddressAddResponse>

}