package com.emirli.eatup.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.emirli.eatup.model.ApiRepository
import com.emirli.eatup.model.entity.cuisine.CuisineListResponse
import com.emirli.eatup.model.entity.restaurant.RestaurantResponse
import com.emirli.eatup.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
)  : ViewModel() {


    fun getRestaurantList(): LiveData<Resource<RestaurantResponse>> {
        return apiRepository.getRestaurantList()
    }

    fun getCuisineList(): LiveData<Resource<CuisineListResponse>> {
        return apiRepository.getCuisineList()
    }




}