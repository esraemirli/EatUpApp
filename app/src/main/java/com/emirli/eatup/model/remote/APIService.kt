package com.emirli.eatup.model.remote

import RestaurantResponse
import com.emirli.eatup.model.entity.login.LoginRequest
import com.emirli.eatup.model.entity.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {

    @POST( "users/login")
    suspend fun login(@Body request : LoginRequest?) : Response<LoginResponse>

    @GET( "/restaurant")
    suspend fun getRestaurants() : Response<RestaurantResponse>
}