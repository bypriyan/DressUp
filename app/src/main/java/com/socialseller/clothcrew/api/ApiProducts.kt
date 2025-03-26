package com.socialseller.clothcrew.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiProducts {

    @GET("collections")
    suspend fun getCollections(
        @Query("pagination[page]") page: Int = 1,
        @Query("pagination[pageSize]") pageSize: Int = 20,
    ): Response<collectionsResponse>

    @GET("banners")
    suspend fun getBanners(
        @Query("populate") populate: String = "*"
    ): Response<BannerResponse>
}

    data class collectionsResponse(
    val data: List<Category>
    )

    data class Category(
        val id: String,
        val name: String,
        val tag: String?,
        val createdAt: String,
        val updatedAt: String,
        val thumbnail: Thumbnail
    )

    data class Thumbnail(
        val id: String,
        val name: String,
        val url: String,
        val formats: CollectionFormat
    )

    data class CollectionFormat(
        val large: ImageFormat?,
        val small: ImageFormat?,
        val medium: ImageFormat?,
        val thumbnail: ImageFormat?
    )

    data class ImageFormat(
        val url: String,
        val width: String,
        val height: String
    )

//bannners
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
    val formats: Formats,
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

data class Formats(
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
