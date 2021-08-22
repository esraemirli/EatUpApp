package com.emirli.eatup.model.entity.profile

import com.google.gson.annotations.SerializedName

data class UserImageResponse(
    @SerializedName("data")
    val image: String
)
