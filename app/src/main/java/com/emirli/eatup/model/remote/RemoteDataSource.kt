package com.emirli.eatup.model.remote

import com.emirli.eatup.model.entity.login.LoginRequest
import com.emirli.eatup.model.entity.basket.BasketRequest
import com.emirli.eatup.model.entity.login.RegisterRequest
import com.emirli.eatup.model.entity.profile.UserRequest
import com.emirli.eatup.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService) :
    BaseDataSource() {

    suspend fun login(request: LoginRequest) = getResult {
        apiService.login(request)
    }

    suspend fun register(request: RegisterRequest) = getResult {
        apiService.register(request)
    }

    suspend fun getRestaurantList() = getResult {
        apiService.getRestaurantList()
    }

    suspend fun getBasketItemList() = getResult {
        apiService.getBasketItemList()
    }

    suspend fun buyBasket() = getResult {
        apiService.buyBasket()
    }

    suspend fun getFavoriteRestaurantList() = getResult {
        apiService.getFavoriteRestaurantList()
    }

    suspend fun getLastOrders() = getResult {
        apiService.getLastOrders()
    }

    suspend fun rateOrder(mealId: Int, vote: Float, cartId: Int) = getResult {
        apiService.rateOrder(mealId, vote, cartId)
    }

    suspend fun getUserDetail() = getResult {
        apiService.getUserDetail()
    }

    suspend fun getUserImage() = getResult {
        apiService.getUserImage()
    }

    suspend fun updateUser(request: UserRequest) = getResult {
        apiService.updateUser(request)
    }

    suspend fun getRestaurantById(restaurantId: Int) = getResult {
        apiService.getRestaurantById(restaurantId)
    }

    suspend fun addFavoriteRestaurant(restaurantId: Int) = getResult {
        apiService.addFavoriteRestaurant(restaurantId)
    }

    suspend fun removeFavoriteRestaurant(restaurantId: Int) = getResult {
        apiService.removeFavoriteRestaurant(restaurantId)
    }

    suspend fun getCuisineList() = getResult {
        apiService.getCuisineList()
    }

    suspend fun getRestaurantsByCuisine(cuisineId: Int) = getResult {
        apiService.getRestaurantsByCuisine(cuisineId)
    }

    suspend fun getMealById(mealId: Int) = getResult {
        apiService.getMealById(mealId)
    }

    suspend fun removeItemFromBasket(mealId: Int) = getResult {
        apiService.removeItemFromBasket(mealId)
    }

    suspend fun addBasket(request: BasketRequest) = getResult {
        apiService.addBasket(request)
    }
}