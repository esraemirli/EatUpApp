package com.emirli.eatup.model.entity.meal

import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("id")
    val id: Int,
    @SerializedName("calorie")
    val calorie: Double,
    @SerializedName("cuisines")
    val cuisines: List<String>,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("ingredients")
    val ingredients: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("quantity")
    val quantity: Double,
    @SerializedName("vote")
    val vote: Double
)