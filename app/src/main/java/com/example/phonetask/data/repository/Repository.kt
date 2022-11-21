package com.example.phonetask.data.repository

import com.example.phonetask.data.api.ProductInstance
import com.example.phonetask.data.model.cart_screen.CartResult
import com.example.phonetask.data.model.main_screen.SearchResult
import com.example.phonetask.data.model.product_detail_screen.DetailResult
import io.reactivex.rxjava3.core.Single

class Repository {

    fun getPhoneSearch(): Single<SearchResult> {
        return ProductInstance.apiPhoneSearch.getPhoneList()
    }

    fun getPhoneDetail(): Single<DetailResult> {
        return ProductInstance.apiPhoneSearch.getDetailPhone()
    }

    fun getPhoneCart(): Single<CartResult> {
        return ProductInstance.apiPhoneSearch.getPhoneCart()
    }
}