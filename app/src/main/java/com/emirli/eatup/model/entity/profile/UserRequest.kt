package com.emirli.eatup.model.entity.profile


import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("address")
    val address: String
)