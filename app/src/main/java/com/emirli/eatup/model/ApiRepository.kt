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

    fun getRestaurantList() = performNetworkOperation {
        remoteDataSource.getRestaurantList()
    }

    fun getRestaurantById(id : Int) = performNetworkOperation {
        remoteDataSource.getRestaurantById(id)
    }

    fun getCuisineList() = performNetworkOperation {
        remoteDataSource.getCuisineList()
    }

}