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
        val formats: Formats
    )

    data class Formats(
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
