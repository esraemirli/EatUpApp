package com.emirli.eatup.model.remote

import com.emirli.eatup.model.entity.cuisine.CuisineListResponse
import com.emirli.eatup.model.entity.favorite.FavoriteStatusResponse
import com.emirli.eatup.model.entity.login.LoginRequest
import com.emirli.eatup.model.entity.login.LoginResponse
import com.emirli.eatup.model.entity.login.RegisterResponse
import com.emirli.eatup.model.entity.meal.MealResponse
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

    @GET( "restaurant/favourite")   //TODO typo
    suspend fun getFavoriteRestaurantList() : Response<RestaurantResponse>

    @GET( "restaurant/{restaurantId}")
    suspend fun getRestaurantById(@Path("restaurantId") restaurantId: Int) : Response<RestaurantResponse>

    @POST( "restaurant/favorite/{restaurantId}")
    suspend fun addFavoriteRestaurant(@Path("restaurantId") restaurantId: Int) : Response<FavoriteStatusResponse>

    @POST( "restaurant/not-favorite/{restaurantId}")
    suspend fun removeFavoriteRestaurant(@Path("restaurantId") restaurantId: Int) : Response<FavoriteStatusResponse>

    @GET( "restaurant/{cuisineId}/cuisine")
    suspend fun getRestaurantsByCuisine(@Path("cuisineId") cuisineId: Int) : Response<RestaurantResponse>

    @GET( "cuisine")
    suspend fun getCuisineList() : Response<CuisineListResponse>

    @GET( "meal/{mealId}")
    suspend fun getMealById(@Path("mealId") mealId: Int) : Response<MealResponse>

}