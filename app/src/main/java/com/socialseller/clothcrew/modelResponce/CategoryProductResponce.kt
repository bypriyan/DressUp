package com.socialseller.clothcrew.modelResponce

class CategoryProductResponce (
    val id: Int,
    val name: String,
    val createdAt: String,
    val updatedAt: String,
    val thumbnail: CategoryProductThumbnail,
    val sub_categories: List<Any>, // Empty in your JSON
    val products: List<Product>
)

data class CategoryProductThumbnail(
    val id: Int,
    val name: String,
    val alternativeText: String?,
    val caption: String?,
    val width: Int,
    val height: Int,
    val formats: Formats,
    val hash: String,
    val ext: String,
    val mime: String,
    val size: Double,
    val url: String,
    val previewUrl: String?,
    val provider: String,
    val provider_metadata: Any?, // Could be more specific if you know the type
    val folderPath: String,
    val createdAt: String,
    val updatedAt: String
)

data class Formats(
    val small: CategoryImageFormat?,
    val thumbnail: CategoryImageFormat?,
    val large: CategoryImageFormat?,
    val medium: CategoryImageFormat?
)

data class CategoryImageFormat(
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

data class Product(
    val id: Int,
    val slug: String,
    val name: String,
    val desc: String,
    val yt_video_link: String,
    val isActive: Boolean,
    val shipping_price: Int,
    val cod_enabled: Boolean,
    val shipping: String,
    val createdAt: String,
    val updatedAt: String,
    val brand: String,
    val rental_duration: Int,
    val security_money_type: String,
    val security_money_value: Int,
    val is_available: Boolean,
    val thumbnail: Thumbnail,
    val product_variants: List<ProductVariant>,
    val sub_category_ssa: Any?, // Could be more specific
    val category_ssa: Int
)

data class ProductVariant(
    val id: Int,
    val name: String,
    val quantity: Int,
    val price: Double,
    val strikePrice: Double,
    val premiumPrice: Double,
    val isActive: Boolean,
    val createdAt: String,
    val updatedAt: String
)