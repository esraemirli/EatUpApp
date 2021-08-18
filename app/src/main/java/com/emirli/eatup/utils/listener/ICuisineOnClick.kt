package com.emirli.eatup.utils.listener

import com.emirli.eatup.model.entity.Cuisine

interface ICuisineOnClick {
    fun onClick(cuisine: Cuisine)
}