package com.emirli.eatup.ui.restaurantlisting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.emirli.eatup.model.ApiRepository
import com.emirli.eatup.model.entity.Restaurant
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantListingViewModel@Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
)  : ViewModel() {

    fun getRestaurantsByCuisine(cuisineId : Int) = apiRepository.getRestaurantsByCuisine(cuisineId)

}