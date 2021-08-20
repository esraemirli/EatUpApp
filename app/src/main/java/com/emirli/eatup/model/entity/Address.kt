package com.emirli.eatup.model.entity


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("city")
    val city: String,
    @SerializedName("houseNumber")
    val houseNumber: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double,
    @SerializedName("postalCode")
    val postalCode: Int,
    @SerializedName("street")
    val street: String
)