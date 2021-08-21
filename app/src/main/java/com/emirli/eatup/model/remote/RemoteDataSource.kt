package com.emirli.eatup.model.remote

import com.emirli.eatup.model.entity.login.LoginRequest
import com.emirli.eatup.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService) :
    BaseDataSource() {

    suspend fun login(request: LoginRequest) = getResult {
        apiService.login(request)
    }

    suspend fun register(request: LoginRequest) = getResult {
        apiService.register(request)
    }

    suspend fun getRestaurantList() = getResult {
        apiService.getRestaurantList()
    }

    suspend fun getFavoriteRestaurantList() = getResult {
        apiService.getFavoriteRestaurantList()
    }

    suspend fun getRestaurantById(restaurantId : Int) = getResult {
        apiService.getRestaurantById(restaurantId)
    }

    suspend fun addFavoriteRestaurant(restaurantId : Int) = getResult {
        apiService.addFavoriteRestaurant(restaurantId)
    }
    suspend fun removeFavoriteRestaurant(restaurantId : Int) = getResult {
        apiService.removeFavoriteRestaurant(restaurantId)
    }

    suspend fun getCuisineList() = getResult {
        apiService.getCuisineList()
    }

    suspend fun getRestaurantsByCuisine(cuisineId : Int) = getResult {
        apiService.getRestaurantsByCuisine(cuisineId)
    }

    suspend fun getMealById(mealId : Int) = getResult {
        apiService.getMealById(mealId)
    }
}