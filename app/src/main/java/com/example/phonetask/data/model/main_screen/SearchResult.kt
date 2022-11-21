package com.example.phonetask.data.model.main_screen

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("best_seller") val best_seller: List<BestSeller>,
    @SerializedName("home_store") val home_store: List<HomeStore>
)