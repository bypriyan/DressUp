package com.socialseller.clothcrew.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bypriyan.bustrackingsystem.utility.Constants
import com.bypriyan.bustrackingsystem.utility.DataStoreManager
import com.google.gson.Gson
import com.socialseller.clothcrew.api.Category
import com.socialseller.clothcrew.api.ErrorResponse
import com.socialseller.clothcrew.api.OtpResponse
import com.socialseller.clothcrew.api.OtpVerifyResponse
import com.socialseller.clothcrew.api.User
import com.socialseller.clothcrew.api.UserInfoResponce
import com.socialseller.clothcrew.api.UserRequest
import com.socialseller.clothcrew.api.UserResponse
import com.socialseller.clothcrew.api.collectionsResponse
import com.socialseller.clothcrew.apiResponce.ApiResponse
import com.socialseller.clothcrew.repository.AuthRepository
import com.socialseller.clothcrew.repository.ProductRepository
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
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _collections = MutableStateFlow<ApiResponse<collectionsResponse>>(ApiResponse.Loading())
    val collections: StateFlow<ApiResponse<collectionsResponse>> = _collections

    init {
        getCollections()
    }

    private fun getCollections() {
        viewModelScope.launch {
            _collections.value = ApiResponse.Loading() // Set loading state
            try {
                val response = productRepository.getCollection()
                _collections.value = response
            } catch (e: Exception) {
                _collections.value = ApiResponse.Error("Unexpected error: ${e.message}")
            }
        }
    }
}
