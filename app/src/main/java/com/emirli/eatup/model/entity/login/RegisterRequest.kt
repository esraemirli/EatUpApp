package com.emirli.eatup.model.entity.login


import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("mail")
    val mail: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String
)