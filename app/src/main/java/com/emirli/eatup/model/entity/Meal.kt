package com.emirli.eatup.model.entity

data class Meal(
    val id : Long,
    val name : String,
    val imageUrl : String,
    val price : Double,
    val calorie : Double,
    val vote : Double,
    val quantity : Double,
    val ingredients : List<String>?
)
