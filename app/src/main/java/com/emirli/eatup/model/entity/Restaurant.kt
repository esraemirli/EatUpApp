package com.emirli.eatup.model.entity



import com.google.gson.annotations.SerializedName

data class Restaurant(
    @SerializedName("id")
    val id: Int,
    @SerializedName("address")
    val address: Address,
    @SerializedName("cuisines")
    val cuisines: List<String>,
    @SerializedName("deliveryTime")
    val deliveryTime: String,
    @SerializedName("detail")
    val detail: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("meals")
    val meals: List<Meal>,
    @SerializedName("minimumPrice")
    val minimumPrice: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("vote")
    val vote: Double
)