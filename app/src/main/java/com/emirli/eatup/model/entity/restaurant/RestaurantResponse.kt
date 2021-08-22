package com.emirli.eatup.model.entity.restaurant

import com.google.gson.annotations.SerializedName

data class RestaurantResponse (
    @SerializedName("data")
    val restaurantList : ArrayList<Restaurant>,
    @SerializedName("success")
    val success : Boolean,
    @SerializedName("count")
    val count : Int
)
