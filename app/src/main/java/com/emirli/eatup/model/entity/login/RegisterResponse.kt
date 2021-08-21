package com.emirli.eatup.model.entity.login

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("data")
    val data: String
)
