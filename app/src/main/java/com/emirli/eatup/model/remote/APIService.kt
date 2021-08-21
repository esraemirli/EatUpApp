package com.emirli.eatup.model.remote

import com.emirli.eatup.model.entity.cuisine.CuisineListResponse
import com.emirli.eatup.model.entity.login.LoginRequest
import com.emirli.eatup.model.entity.login.LoginResponse
import com.emirli.eatup.model.entity.login.RegisterResponse
import com.emirli.eatup.model.entity.restaurant.RestaurantResponse
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @POST( "users/login")
    suspend fun login(@Body request : LoginRequest?) : Response<LoginResponse>

    @POST( "users/register")
    suspend fun register(@Body request : LoginRequest?) : Response<RegisterResponse>

    @GET( "restaurant")
    suspend fun getRestaurantList() : Response<RestaurantResponse>

    @GET( "restaurant/{restaurantId}")
    suspend fun getRestaurantById(@Path("restaurantId") id: Int) : Response<RestaurantResponse>

    @GET( "cuisine")
    suspend fun getCuisineList() : Response<CuisineListResponse>
}