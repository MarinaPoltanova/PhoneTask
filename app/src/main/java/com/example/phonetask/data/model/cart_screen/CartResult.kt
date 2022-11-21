package com.example.phonetask.data.model.cart_screen

data class CartResult(
    val basket: List<Basket>,
    val delivery: String,
    val id: String,
    val total: Int
)