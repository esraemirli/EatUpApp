package com.emirli.eatup.utils.listener

interface IOrderRatingOnClick {
    fun onClick(vote: Float, mealId: Int, cartId : Int)
}