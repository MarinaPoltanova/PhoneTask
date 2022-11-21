package com.example.phonetask.data.api

import com.example.phonetask.data.model.cart_screen.CartResult
import com.example.phonetask.data.model.main_screen.SearchResult
import com.example.phonetask.data.model.product_detail_screen.DetailResult
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @GET("v3/654bd15e-b121-49ba-a588-960956b15175")
    fun getPhoneList(): Single<SearchResult>

    @GET("v3/6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    fun getDetailPhone(): Single<DetailResult>

    @GET("v3/53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    fun getPhoneCart(): Single<CartResult>
}