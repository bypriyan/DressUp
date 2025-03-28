package com.socialseller.clothcrew.api

import com.socialseller.clothcrew.modelResponce.BannerResponse
import com.socialseller.clothcrew.modelResponce.CategoriesResponse
import com.socialseller.clothcrew.modelResponce.CategoryProductResponce
import com.socialseller.clothcrew.modelResponce.SellerStoreResponce
import com.socialseller.clothcrew.modelResponce.collectionsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

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

    @GET("custom/categories")
    suspend fun getcategories(): Response<CategoriesResponse>

    @GET("seller-infos")
    suspend fun getNearStore(
        @QueryMap(encoded = false) map: Map<String, String>
    ): Response<SellerStoreResponce>

    @GET("custom/categories/{id}")
    suspend fun getCategoryProducts(@Path("id") id: String): Response<CategoryProductResponce>

}

