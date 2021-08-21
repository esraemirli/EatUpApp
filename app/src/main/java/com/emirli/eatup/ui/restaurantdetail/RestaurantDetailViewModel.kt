package com.emirli.eatup.ui.restaurantdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.emirli.eatup.model.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) :  ViewModel() {

    fun getRestaurantById(id : Int) = apiRepository.getRestaurantById(id)
}

