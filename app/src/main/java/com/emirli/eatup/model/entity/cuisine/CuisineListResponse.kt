package com.emirli.eatup.model.entity.cuisine


import com.emirli.eatup.model.entity.Cuisine
import com.google.gson.annotations.SerializedName

data class CuisineListResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("data")
    val cuisineList: ArrayList<Cuisine>,
    @SerializedName("success")
    val success: Boolean
)