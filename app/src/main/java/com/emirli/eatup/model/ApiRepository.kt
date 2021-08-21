package com.emirli.eatup.model

import com.emirli.eatup.model.entity.login.LoginRequest
import com.emirli.eatup.model.local.LocalDataSource
import com.emirli.eatup.model.remote.APIService
import com.emirli.eatup.model.remote.RemoteDataSource
import com.emirli.eatup.utils.performAuthTokenNetworkOperation
import com.emirli.eatup.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private val apiService: APIService,
    private var localDataSource: LocalDataSource
) {
    //Profile
    fun login(request: LoginRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.login(request)
        },
        saveToken = {
           localDataSource.saveToken(it)
        }
    )

    fun register(request: LoginRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.register(request)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )

    // Restaurants
    fun getRestaurantList() = performNetworkOperation {
        remoteDataSource.getRestaurantList()
    }
    fun getFavoriteRestaurantList() = performNetworkOperation {
        remoteDataSource.getFavoriteRestaurantList()
    }

    fun getRestaurantById(restaurantId : Int) = performNetworkOperation {
        remoteDataSource.getRestaurantById(restaurantId)
    }

    fun addFavoriteRestaurant(restaurantId : Int) = performNetworkOperation {
        remoteDataSource.addFavoriteRestaurant(restaurantId)
    }
    fun removeFavoriteRestaurant(restaurantId : Int) = performNetworkOperation {
        remoteDataSource.removeFavoriteRestaurant(restaurantId)
    }

    fun getRestaurantsByCuisine(cuisineId : Int) = performNetworkOperation {
        remoteDataSource.getRestaurantsByCuisine(cuisineId)
    }

    //Cuisines
    fun getCuisineList() = performNetworkOperation {
        remoteDataSource.getCuisineList()
    }

    //Meal
    fun getMealById(mealId : Int) = performNetworkOperation {
        remoteDataSource.getMealById(mealId)
    }


}