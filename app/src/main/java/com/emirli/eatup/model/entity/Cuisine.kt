package com.emirli.eatup.model.entity
import com.google.gson.annotations.SerializedName

data class Cuisine(
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("name")
    val name: String
)