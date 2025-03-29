package com.socialseller.clothcrew.utility

import android.util.Log
import com.socialseller.clothcrew.apiResponce.ApiResponse

object ResponceHelper {
     fun <T> handleApiResponse(
        response: ApiResponse<T>,
        onSuccess: (T) -> Unit,
        logTag: String
    ) {
        when (response) {
            is ApiResponse.Loading -> {
                // Show a loading state if needed
            }
            is ApiResponse.Success -> {
                response.data?.let(onSuccess)
            }
            is ApiResponse.Error -> {
                Log.d(logTag, "Error fetching $logTag: ${response.message}")
            }
        }
    }

}
