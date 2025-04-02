package com.socialseller.clothcrew.modelResponce

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    val categories: List<Category>,
    val meta: Meta
)

data class Category(
    val id: Int,
    val name: String,
    val createdAt: String,
    val updatedAt: String,
    val thumbnail: ThumbnailCategory,
    val sub_categories: List<Any> // Empty in your JSON, adjust if needed
)

data class ThumbnailCategory(
    val id: Int,
    val name: String,
    val alternativeText: String?,
    val caption: String?,
    val width: Int,
    val height: Int,
    val formats: CategoryFormats,
    val hash: String,
    val ext: String,
    val mime: String,
    val size: Double,
    val url: String,
    val previewUrl: String?,
    val provider: String,
    val provider_metadata: Any?, // Could be more specific if you know the structure
    val folderPath: String,
    val createdAt: String,
    val updatedAt: String
)

data class CategoryFormats(
    @SerializedName("thumbnail") val thumbnailFormat: ImageFormatCategory?,
    @SerializedName("small") val small: ImageFormatCategory?,
    @SerializedName("medium") val medium: ImageFormatCategory?
    // Add other format sizes if they appear in your data
)

data class ImageFormatCategory(
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