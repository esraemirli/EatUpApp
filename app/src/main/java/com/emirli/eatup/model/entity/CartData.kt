package com.emirli.eatup.model.entity


import com.google.gson.annotations.SerializedName

data class CartData(
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("ingredients")
    val ingredients: List<String>,
    @SerializedName("mealId")
    val mealId: Int,
    @SerializedName("restaurantName")
    val restaurantName: String,
    @SerializedName("rate")
    val rate: Float? = null,
    @SerializedName("cartId")
    val cartId: Int,
    @SerializedName("mealName")
    val mealName: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("quantity")
    val quantity: Int
)