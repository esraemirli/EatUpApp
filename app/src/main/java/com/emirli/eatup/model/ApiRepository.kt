package com.emirli.eatup.model

import com.emirli.eatup.model.entity.login.LoginRequest
import com.emirli.eatup.model.local.LocalDataSource
import com.emirli.eatup.model.remote.RemoteDataSource
import com.emirli.eatup.utils.performAuthTokenNetworkOperation
import com.emirli.eatup.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private var localDataSource: LocalDataSource
){
//    fun login(request: LoginRequest) = performAuthTokenNetworkOperation(
//        call = {
//            remoteDataSource.login(request)
//        },
//        saveToken = {
//           localDataSource.saveToken(it)
//        }
//    )

//    fun getRestaurants() = performNetworkOperation { remoteDataSource.getRestaurants() }
}