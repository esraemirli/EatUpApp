package com.emirli.eatup.utils.listener

import com.emirli.eatup.model.entity.restaurant.Restaurant

interface IRestaurantOnClick {
    fun onClick(restaurant: Restaurant)
}