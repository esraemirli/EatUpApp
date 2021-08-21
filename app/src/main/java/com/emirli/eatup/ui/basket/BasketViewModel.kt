package com.emirli.eatup.ui.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.emirli.eatup.model.ApiRepository
import com.emirli.eatup.model.DataResponse
import com.emirli.eatup.model.entity.basket.BasketResponse
import com.emirli.eatup.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BasketViewModel  @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
)  : ViewModel() {


    fun getBasketItemList() : LiveData<Resource<BasketResponse>> {
        return apiRepository.getBasketItemList()
    }

    fun buyBasket() : LiveData<Resource<DataResponse>> {
        return apiRepository.buyBasket()
    }

    fun removeItemFromBasket(mealId: Int) : LiveData<Resource<DataResponse>> {
        return apiRepository.removeItemFromBasket(mealId)
    }

}