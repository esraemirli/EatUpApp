package com.emirli.eatup.model.entity.order


import com.google.gson.annotations.SerializedName

data class Basket(
    @SerializedName("cartDatas")
    val cartDataList: ArrayList<CartData>,
    @SerializedName("totalPrice")
    val totalPrice: Int
)