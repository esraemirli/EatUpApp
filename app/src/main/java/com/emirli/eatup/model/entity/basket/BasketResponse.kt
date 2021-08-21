package com.emirli.eatup.model.entity.basket


import com.emirli.eatup.model.entity.Basket
import com.google.gson.annotations.SerializedName

data class BasketResponse(
    @SerializedName("data")
    val basketData: Basket
)