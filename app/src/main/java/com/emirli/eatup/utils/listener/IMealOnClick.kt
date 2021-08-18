package com.emirli.eatup.utils.listener

import com.emirli.eatup.model.entity.Meal

interface IMealOnClick {
    fun onClick(meal: Meal)
    fun onClickBasket(meal: Meal)
}