package com.socialseller.clothcrew.modelResponce

data class ProductDetailsApiResponceDT(
    val product: ProductDT,
    val randomProducts: List<RandomProductDT>
)

data class ProductDT(
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
    val size_chart: SizeChartDT,
    val gallery: List<GalleryItemDT>,
    val product_variants: List<ProductVariantDT>,
    val category: CategoryDT,
    val thumbnail: ThumbnailDT,
    val msg: String
)

data class RandomProductDT(
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
    val product_variants: List<RandomProductVariantDT>,
    val thumbnail: ThumbnailDT
)

data class SizeChartDT(
    val measurements: List<MeasurementDT>
)

data class MeasurementDT(
    val name: String,
    val value: List<ValueDT>
)

data class ValueDT(
    val data: String
)

data class GalleryItemDT(
    val id: Int,
    val name: String,
    val alternativeText: String?,
    val caption: String?,
    val width: Int,
    val height: Int,
    val formats: FormatsDT,
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

data class FormatsDT(
    val small: FormatItemDT?,
    val medium: FormatItemDT?,
    val thumbnail: FormatItemDT
)

data class FormatItemDT(
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

data class ProductVariantDT(
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

data class RandomProductVariantDT(
    val id: Int,
    val name: String,
    val quantity: Int,
    val price: Double,
    val strikePrice: Double,
    val premiumPrice: Double?,
    val isActive: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val measurement: List<MeasurementItemDT>?,
    val isSaved: Boolean
)

data class MeasurementItemDT(
    val name: String,
    val value: String
)

data class CategoryDT(
    val id: Int,
    val name: String,
    val createdAt: String,
    val updatedAt: String
)

data class ThumbnailDT(
    val id: Int,
    val name: String,
    val alternativeText: String?,
    val caption: String?,
    val width: Int,
    val height: Int,
    val formats: ThumbnailFormatsDT,
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

data class ThumbnailFormatsDT(
    val small: FormatItemDT?,
    val medium: FormatItemDT?,
    val thumbnail: FormatItemDT
)