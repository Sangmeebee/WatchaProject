package com.sangmee.watchaproject.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://itunes.apple.com/search/"

object RetrofitClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient().newBuilder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    fun getService(): RetrofitService = retrofit.create(RetrofitService::class.java)
}
