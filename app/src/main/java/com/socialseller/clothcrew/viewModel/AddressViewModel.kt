package com.socialseller.clothcrew.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bypriyan.bustrackingsystem.utility.Constants
import com.bypriyan.bustrackingsystem.utility.DataStoreManager
import com.socialseller.clothcrew.apiResponce.ApiResponse
import com.socialseller.clothcrew.modelResponce.ProductDetailsApiResponceDT
import com.socialseller.clothcrew.models.address.post.AddressAddReq
import com.socialseller.clothcrew.models.address.post.AddressAddResponse
import com.socialseller.clothcrew.repository.AddressRepository
import com.socialseller.clothcrew.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val addressRepository: AddressRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _addressAdd = MutableStateFlow<ApiResponse<AddressAddResponse>>(ApiResponse.Loading())
    val addressAdd: StateFlow<ApiResponse<AddressAddResponse>> = _addressAdd

    fun addAddress( address: AddressAddReq) {
        viewModelScope.launch {
            _addressAdd.value = ApiResponse.Loading() // Set loading state
            dataStoreManager.getString(Constants.KEY_USER_TOKEN).collect { token ->
                token?.let {
                    try {
                        val response = addressRepository.addAddress("Bearer $token", address)
                        _addressAdd.value = response
                    } catch (e: Exception) {
                        _addressAdd.value = ApiResponse.Error("Unexpected error: ${e.message}")
                    }
                }
            }
        }
    }

}