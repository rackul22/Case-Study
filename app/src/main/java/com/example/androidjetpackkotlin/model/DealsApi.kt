package com.example.androidjetpackkotlin.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DealsApi {
    /*add the endpoint*/
    @GET("deals")
    fun getProductListing(): Single<Model>

    @GET(value = "deals/{id}")
    fun getProductDetail(@Path("id") id: Int?): Single<ProductDetail>
}