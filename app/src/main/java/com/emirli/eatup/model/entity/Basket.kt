package com.emirli.eatup.model.entity


import com.emirli.eatup.model.entity.CartData
import com.google.gson.annotations.SerializedName

data class Basket(
    @SerializedName("cartDatas")
    val cartDataList: ArrayList<CartData>,
    @SerializedName("totalPrice")
    val totalPrice: Int,
    @SerializedName("time")
    val orderTime: String
)