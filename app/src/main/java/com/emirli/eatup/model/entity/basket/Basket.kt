package com.emirli.eatup.model.entity.basket


import com.google.gson.annotations.SerializedName

data class Basket(
    @SerializedName("cartDatas")
    val cartDataList: ArrayList<CartData>,
    @SerializedName("totalPrice")
    val totalPrice: Double,
    @SerializedName("time")
    val orderTime: String
)