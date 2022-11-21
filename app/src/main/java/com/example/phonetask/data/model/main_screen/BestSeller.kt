package com.example.phonetask.data.model.main_screen

import com.google.gson.annotations.SerializedName

data class BestSeller(
    @SerializedName("discount_price") val discount_price: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("is_favorites") val is_favorites: Boolean,
    @SerializedName("picture") val picture: String,
    @SerializedName("price_without_discount") val price_without_discount: Int,
    @SerializedName("title") val title: String
)