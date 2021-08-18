package com.emirli.eatup.model

import com.emirli.eatup.model.local.LocalDataSource
import com.emirli.eatup.model.remote.RemoteDataSource
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private var localDataSource: LocalDataSource
) {

}