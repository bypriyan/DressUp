package com.socialseller.clothcrew.modelResponce

import com.google.gson.annotations.SerializedName

data class SellerStoreResponce(
    val data: List<Business>,
    val meta: Meta
)

data class Business(
    val id: Int,
    val address: String,
    val business_name: String,
    val total_products: Int,
    val total_orders: Int?,
    val createdAt: String,
    val updatedAt: String,
    val rzp_linked_account_id: String,
    val total_visits: Int,
    val revenue_generated: Double?,
    val latitude: Double?,
    val longitude: Double?,
    val open_time: String,
    val close_time: String,
    val seller: Seller,
    val business_logo: BusinessImage,
    val cover_image: BusinessImage
)

data class Seller(
    val id: Int,
    val username: String,
    val email: String,
    val phone: String,
    val countryCode: String
)

data class BusinessImage(
    val id: Int,
    val name: String,
    val alternativeText: String?,
    val caption: String?,
    val width: Int,
    val height: Int,
    val formats: ImageFormats,
    val hash: String,
    val ext: String,
    val mime: String,
    val size: Double,
    val url: String,
    val previewUrl: String?,
    val provider: String,
    val provider_metadata: Any?,
    val folderPath: String,
    val createdAt: String,
    val updatedAt: String
)

data class ImageFormats(
    val thumbnail: ImageFormatSeller?,
    val small: ImageFormatSeller?,
    val medium: ImageFormatSeller?,
    val large: ImageFormatSeller?
)

data class ImageFormatSeller(
    val ext: String,
    val url: String,
    val hash: String,
    val mime: String,
    val name: String,
    val path: String?,
    val size: Double,
    val width: Int,
    val height: Int
)

