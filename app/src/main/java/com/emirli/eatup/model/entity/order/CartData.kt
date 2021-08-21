package com.emirli.eatup.model.entity.order


import com.google.gson.annotations.SerializedName

data class CartData(
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("ingredients")
    val ingredients: List<String>,
    @SerializedName("mealId")
    val mealId: Int,
    @SerializedName("mealName")
    val mealName: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("quantity")
    val quantity: Int
)