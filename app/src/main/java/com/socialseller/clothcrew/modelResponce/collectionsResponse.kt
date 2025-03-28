package com.socialseller.clothcrew.modelResponce

data class collectionsResponse(
    val data: List<Categorycollection>
)

data class Categorycollection(
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
