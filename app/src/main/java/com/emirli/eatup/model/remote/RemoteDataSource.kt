package com.emirli.eatup.model.remote

import com.emirli.eatup.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService) : BaseDataSource()  {
}