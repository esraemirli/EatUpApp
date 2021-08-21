package com.emirli.eatup.utils.listener

import com.emirli.eatup.model.entity.order.CartData

interface ICartOnClick {
    fun onClick(cart: CartData)
}