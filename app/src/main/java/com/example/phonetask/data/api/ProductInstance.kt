package com.example.phonetask.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ProductInstance {

    val interceptor : HttpLoggingInterceptor =  HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build();


    private val phoneInstance =
        Retrofit.Builder()
            .baseUrl("https://run.mocky.io/").client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()


    val apiPhoneSearch: ApiService =
        phoneInstance.create<ApiService>()
}