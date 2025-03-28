package com.socialseller.clothcrew.modelResponce

data class BannerResponse(
    val data: List<BannerData>,
    val meta: Meta
)

data class BannerData(
    val id: Int,
    val attributes: BannerAttributes
)

data class BannerAttributes(
    val Title: String,
    val type: String,
    val data: String,
    val createdAt: String,
    val updatedAt: String,
    val image: ImageWrapper
)

data class ImageWrapper(
    val data: ImageData
)

data class ImageData(
    val id: Int,
    val attributes: ImageAttributes
)

data class ImageAttributes(
    val name: String,
    val alternativeText: String?,
    val caption: String?,
    val width: Int,
    val height: Int,
    val formats: BannerFormats,
    val hash: String,
    val ext: String,
    val mime: String,
    val size: Double,
    val url: String,
    val previewUrl: String?,
    val provider: String,
    val provider_metadata: String?,
    val createdAt: String,
    val updatedAt: String
)

data class BannerFormats(
    val small: FormatDetails?,
    val medium: FormatDetails?,
    val thumbnail: FormatDetails?
)

data class FormatDetails(
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

data class Meta(
    val pagination: Pagination
)

data class Pagination(
    val page: Int,
    val pageSize: Int,
    val pageCount: Int,
    val total: Int
)