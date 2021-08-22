package com.emirli.eatup.model.entity.profile


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("imageUrl")
    val imageUrl: String? = null,
    @SerializedName("mail")
    val mail: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("address")
    val address: String
)