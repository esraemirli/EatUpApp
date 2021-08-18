package com.emirli.eatup.model.entity

import java.io.Serializable

data class Restaurant (
    val id : Long,
    val name : String,
    val detail : String,
    val phone : String,
    val imageUrl : String,
    val meals : List<Meal>,
    val deliveryTime : String,
    val minPrice : String,
    val vote : String,
) : Serializable