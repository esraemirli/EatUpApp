package com.emirli.eatup.model

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("data")
    val isSuccess: Boolean
)
