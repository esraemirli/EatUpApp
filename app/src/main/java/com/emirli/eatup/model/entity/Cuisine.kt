package com.emirli.eatup.model.entity

import java.io.Serializable

data class Cuisine (
    val id : Long,
    val imageUrl : String,
    val name : String,
) : Serializable