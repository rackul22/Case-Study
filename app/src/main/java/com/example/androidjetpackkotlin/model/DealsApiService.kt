package com.example.androidjetpackkotlin.model

import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DealsApiService {

    private val BASEURL = "https://api.target.com/mobile_case_study_deals/v1/"


   fun getRetrofitClient():OkHttpClient.Builder{
       val interceptor = HttpLoggingInterceptor()
       interceptor.level = HttpLoggingInterceptor.Level.BODY
       val okHttpClient = OkHttpClient.Builder()
       return okHttpClient.addInterceptor(interceptor)
   }

    private val api = Retrofit.Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(getRetrofitClient().build())
        .build()
        .create(DealsApi::class.java)

    fun getProductListing(): Single<Model> {
        return api.getProductListing()
    }

    fun getProductDetails(id: Int): Single<ProductDetail> {
        return api.getProductDetail(id)
    }
}