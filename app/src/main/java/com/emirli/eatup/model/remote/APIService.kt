package com.emirli.eatup.model.remote

import com.emirli.eatup.model.entity.DataResponse
import com.emirli.eatup.model.entity.profile.User
import com.emirli.eatup.model.entity.cuisine.CuisineListResponse
import com.emirli.eatup.model.entity.login.LoginRequest
import com.emirli.eatup.model.entity.login.LoginResponse
import com.emirli.eatup.model.entity.meal.MealResponse
import com.emirli.eatup.model.entity.basket.BasketRequest
import com.emirli.eatup.model.entity.basket.BasketResponse
import com.emirli.eatup.model.entity.lastorder.LastOrderResponse
import com.emirli.eatup.model.entity.login.RegisterRequest
import com.emirli.eatup.model.entity.profile.UserImageResponse
import com.emirli.eatup.model.entity.profile.UserRequest
import com.emirli.eatup.model.entity.restaurant.RestaurantResponse
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @POST("users/login")
    suspend fun login(@Body request: LoginRequest?): Response<LoginResponse>

    @POST("users/register")
    suspend fun register(@Body request: RegisterRequest?): Response<LoginResponse>

    @GET("restaurant")
    suspend fun getRestaurantList(): Response<RestaurantResponse>

    @GET("cart")
    suspend fun getBasketItemList(): Response<BasketResponse>

    @POST("cart/buy")
    suspend fun buyBasket(): Response<DataResponse>

    @GET("restaurant/favourite")
    suspend fun getFavoriteRestaurantList(): Response<RestaurantResponse>

    @GET("cart/sold-meals")
    suspend fun getLastOrders(): Response<LastOrderResponse>

    @POST("meal/rate/{mealId}")
    suspend fun rateOrder(
        @Path("mealId") mealId: Int,
        @Query("vote") vote: Float,
        @Query("cartId") cartId: Int
    ): Response<DataResponse>

    @GET("users/get-user")
    suspend fun getUserDetail(): Response<User>

    @GET("users/get-user-image")
    suspend fun getUserImage(): Response<UserImageResponse>

    @GET("restaurant/{restaurantId}")
    suspend fun getRestaurantById(@Path("restaurantId") restaurantId: Int): Response<RestaurantResponse>

    @POST("restaurant/favorite/{restaurantId}")
    suspend fun addFavoriteRestaurant(@Path("restaurantId") restaurantId: Int): Response<DataResponse>

    @POST("restaurant/not-favorite/{restaurantId}")
    suspend fun removeFavoriteRestaurant(@Path("restaurantId") restaurantId: Int): Response<DataResponse>

    @GET("restaurant/{cuisineId}/cuisine")
    suspend fun getRestaurantsByCuisine(@Path("cuisineId") cuisineId: Int): Response<RestaurantResponse>

    @GET("cuisine")
    suspend fun getCuisineList(): Response<CuisineListResponse>

    @GET("meal/{mealId}")
    suspend fun getMealById(@Path("mealId") mealId: Int): Response<MealResponse>

    @POST("cart/remove/{mealId}")
    suspend fun removeItemFromBasket(@Path("mealId") mealId: Int): Response<DataResponse>

    @POST("cart/add")
    suspend fun addBasket(@Body request: BasketRequest): Response<DataResponse>

    @POST("users/update-user")
    suspend fun updateUser(@Body request: UserRequest): Response<DataResponse>

}