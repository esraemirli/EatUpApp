package com.emirli.eatup.model.entity.favorite

import com.google.gson.annotations.SerializedName

data class FavoriteStatusResponse(
    @SerializedName("data")
    val isSuccess: Boolean
)