package com.emirli.eatup.model.remote

import com.emirli.eatup.model.entity.login.LoginRequest
import com.emirli.eatup.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService) :
    BaseDataSource() {

    suspend fun login(request: LoginRequest) = getResult {
        apiService.login(request)
    }

    suspend fun getRestaurants() = getResult {
        apiService.getRestaurants()
    }

}