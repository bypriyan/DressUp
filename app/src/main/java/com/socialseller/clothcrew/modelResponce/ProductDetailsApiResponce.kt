package com.socialseller.clothcrew.modelResponce

import java.util.*

data class ProductDetailsApiResponce(
    val product: Product,
    val randomProducts: List<RandomProduct>
)

data class Product(
    val id: Int,
    val name: String,
    val slug: String,
    val desc: String,
    val yt_video_link: String?,
    val cod_enabled: Boolean,
    val shipping_price: Int?,
    val shipping: String,
    val rental_duration: Int,
    val security_money_type: String,
    val security_money_value: Int,
    val is_available: Boolean,
    val size_chart: SizeChart,
    val gallery: List<GalleryItem>,
    val product_variants: List<ProductVariant>,
    val category: Category,
    val thumbnail: Thumbnail,
    val msg: String
)

data class RandomProduct(
    val id: Int,
    val slug: String,
    val name: String,
    val desc: String,
    val yt_video_link: String?,
    val isActive: Boolean,
    val shipping_price: Int?,
    val cod_enabled: Boolean,
    val shipping: String,
    val createdAt: String,
    val updatedAt: String,
    val brand: String?,
    val rental_duration: Int,
    val security_money_type: String,
    val security_money_value: Int,
    val is_available: Boolean,
    val product_variants: List<RandomProductVariant>,
    val thumbnail: Thumbnail
)

data class SizeChart(
    val measurements: List<Measurement>
)

data class Measurement(
    val name: String,
    val value: List<Value>
)

data class Value(
    val data: String
)

data class GalleryItem(
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
    val provider_metadata: String?,
    val folderPath: String,
    val createdAt: String,
    val updatedAt: String
)

data class Formats(
    val small: FormatItem?,
    val medium: FormatItem?,
    val thumbnail: FormatItem
)

data class FormatItem(
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

data class ProductVariant(
    val id: Int,
    val name: String,
    val quantity: Int,
    val price: Int,
    val strikePrice: Int,
    val premiumPrice: Int?,
    val isActive: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val measurement: Any?,
    val isSaved: Boolean
)

data class RandomProductVariant(
    val id: Int,
    val name: String,
    val quantity: Int,
    val price: Double,
    val strikePrice: Double,
    val premiumPrice: Double?,
    val isActive: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val measurement: List<MeasurementItem>?,
    val isSaved: Boolean
)

data class MeasurementItem(
    val name: String,
    val value: String
)

data class Category(
    val id: Int,
    val name: String,
    val createdAt: String,
    val updatedAt: String
)

data class Thumbnail(
    val id: Int,
    val name: String,
    val alternativeText: String?,
    val caption: String?,
    val width: Int,
    val height: Int,
    val formats: ThumbnailFormats,
    val hash: String,
    val ext: String,
    val mime: String,
    val size: Double,
    val url: String,
    val previewUrl: String?,
    val provider: String,
    val provider_metadata: String?,
    val folderPath: String,
    val createdAt: String,
    val updatedAt: String
)

data class ThumbnailFormats(
    val small: FormatItem?,
    val medium: FormatItem?,
    val thumbnail: FormatItem
)