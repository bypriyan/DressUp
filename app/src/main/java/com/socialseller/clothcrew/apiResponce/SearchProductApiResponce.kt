package com.socialseller.clothcrew.apiResponce

import com.google.gson.annotations.SerializedName
import com.socialseller.clothcrew.modelResponce.Meta

data class SearchProductApiResponce (
    @SerializedName("data") val data: List<ProductSearch>,
    @SerializedName("meta") val meta: Meta
)

data class ProductSearch(
    @SerializedName("id") val id: Int,
    @SerializedName("slug") val slug: String,
    @SerializedName("name") val name: String,
    @SerializedName("desc") val description: String,
    @SerializedName("yt_video_link") val youtubeVideoLink: String?,
    @SerializedName("isActive") val isActive: Boolean,
    @SerializedName("shipping_price") val shippingPrice: Double?,
    @SerializedName("cod_enabled") val codEnabled: Boolean,
    @SerializedName("shipping") val shipping: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String,
    @SerializedName("brand") val brand: String,
    @SerializedName("rental_duration") val rentalDuration: Int,
    @SerializedName("security_money_type") val securityMoneyType: String,
    @SerializedName("security_money_value") val securityMoneyValue: Int,
    @SerializedName("is_available") val isAvailable: Boolean,
    @SerializedName("thumbnail") val thumbnail: ThumbnailSearchProduct,
    @SerializedName("gallery") val gallery: List<GalleryImage>?,
    @SerializedName("product_variants") val productVariants: List<ProductVariant>
)

data class ThumbnailSearchProduct(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("alternativeText") val alternativeText: String?,
    @SerializedName("caption") val caption: String?,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("formats") val formats: ImageFormatsSearch?,
    @SerializedName("hash") val hash: String,
    @SerializedName("ext") val ext: String,
    @SerializedName("mime") val mime: String,
    @SerializedName("size") val size: Double,
    @SerializedName("url") val url: String,
    @SerializedName("previewUrl") val previewUrl: String?,
    @SerializedName("provider") val provider: String,
    @SerializedName("provider_metadata") val providerMetadata: String?,
    @SerializedName("folderPath") val folderPath: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String
)

data class GalleryImage(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("alternativeText") val alternativeText: String?,
    @SerializedName("caption") val caption: String?,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("formats") val formats: ImageFormatsSearch?,
    @SerializedName("hash") val hash: String,
    @SerializedName("ext") val ext: String,
    @SerializedName("mime") val mime: String,
    @SerializedName("size") val size: Double,
    @SerializedName("url") val url: String,
    @SerializedName("previewUrl") val previewUrl: String?,
    @SerializedName("provider") val provider: String,
    @SerializedName("provider_metadata") val providerMetadata: String?,
    @SerializedName("folderPath") val folderPath: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String
)

data class ImageFormatsSearch(
    @SerializedName("large") val large: ImageFormatSearch?,
    @SerializedName("small") val small: ImageFormatSearch?,
    @SerializedName("medium") val medium: ImageFormatSearch?,
    @SerializedName("thumbnail") val thumbnail: ImageFormatSearch?
)

data class ImageFormatSearch(
    @SerializedName("ext") val ext: String,
    @SerializedName("url") val url: String,
    @SerializedName("hash") val hash: String,
    @SerializedName("mime") val mime: String,
    @SerializedName("name") val name: String,
    @SerializedName("path") val path: String?,
    @SerializedName("size") val size: Double,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int
)

data class ProductVariant(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("price") val price: Double,
    @SerializedName("strikePrice") val strikePrice: Double,
    @SerializedName("premiumPrice") val premiumPrice: Double?,
    @SerializedName("isActive") val isActive: Boolean,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String
)

