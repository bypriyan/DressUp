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
import com.socialseller.clothcrew.api.UserInfoResponce
import com.socialseller.clothcrew.api.UserRequest
import com.socialseller.clothcrew.api.UserResponse
import com.socialseller.clothcrew.apiResponce.ApiResponse
import com.socialseller.clothcrew.apiResponce.SearchProductApiResponce
import com.socialseller.clothcrew.modelResponce.BannerResponse
import com.socialseller.clothcrew.modelResponce.CategoriesResponse
import com.socialseller.clothcrew.modelResponce.CategoryProductResponce
import com.socialseller.clothcrew.modelResponce.SellerStoreResponce
import com.socialseller.clothcrew.modelResponce.collectionsResponse
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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.Query
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _collections = MutableStateFlow<ApiResponse<collectionsResponse>>(ApiResponse.Loading())
    val collections: StateFlow<ApiResponse<collectionsResponse>> = _collections

    private val _banners = MutableStateFlow<ApiResponse<BannerResponse>>(ApiResponse.Loading())
    val banners: StateFlow<ApiResponse<BannerResponse>> = _banners

    private val _categories = MutableStateFlow<ApiResponse<CategoriesResponse>>(ApiResponse.Loading())
    val categories: StateFlow<ApiResponse<CategoriesResponse>> = _categories

    private val _stores = MutableStateFlow<ApiResponse<SellerStoreResponce>>(ApiResponse.Loading())
    val stores: StateFlow<ApiResponse<SellerStoreResponce>> = _stores

    private val _categoryProducts = MutableStateFlow<ApiResponse<CategoryProductResponce>>(ApiResponse.Loading())
    val categoryProducts: StateFlow<ApiResponse<CategoryProductResponce>> = _categoryProducts

    private val _searchProduct = MutableStateFlow<ApiResponse<SearchProductApiResponce>>(ApiResponse.Loading())
    val searchProduct: StateFlow<ApiResponse<SearchProductApiResponce>> = _searchProduct

    init {
        getCollections()
        getBanners()
        getCategories()
        fetchTokenAndGetStore() // Fetch token and call getStore
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

    private fun getBanners() {
        viewModelScope.launch {
            _banners.value = ApiResponse.Loading() // Set loading state
            try {
                val response = productRepository.getBanners()
                _banners.value = response
            } catch (e: Exception) {
                _banners.value = ApiResponse.Error("Unexpected error: ${e.message}")
            }
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            _categories.value = ApiResponse.Loading() // Set loading state
            try {
                val response = productRepository.getcategories()
                _categories.value = response
            } catch (e: Exception) {
                _categories.value = ApiResponse.Error("Unexpected error: ${e.message}")
            }
        }
    }

     fun getCategoriesProduct(id: String) {
        viewModelScope.launch {
            _categoryProducts.value = ApiResponse.Loading() // Set loading state
            try {
                val response = productRepository.getCategoryProduct(id)
                _categoryProducts.value = response
            } catch (e: Exception) {
                _categoryProducts.value = ApiResponse.Error("Unexpected error: ${e.message}")
            }
        }
    }

    fun getSearchProduct(query: String) {
        viewModelScope.launch {
            _searchProduct.value = ApiResponse.Loading() // Set loading state
            try {
                val response = productRepository.getSearchProduct(query)
                _searchProduct.value = response
            } catch (e: Exception) {
                _searchProduct.value = ApiResponse.Error("Unexpected error: ${e.message}")
            }
        }
    }


    private fun getStore(token: String) {
        viewModelScope.launch {
            _stores.value = ApiResponse.Loading() // Set loading state
            try {
                val queryMap = hashMapOf<String, String>()
                queryMap["pagination[page]"] = "0"
                queryMap["pagination[size]"] = "10000"
                val response = productRepository.getStore(token, queryMap)
                _stores.value = response
            } catch (e: Exception) {
                _stores.value = ApiResponse.Error("Unexpected error: ${e.message}")
            }
        }
    }

    private fun fetchTokenAndGetStore() {
        viewModelScope.launch {
            dataStoreManager.getString(Constants.KEY_USER_TOKEN).collect { token ->
                token?.let { getStore(it) }
            }
        }
    }
}
