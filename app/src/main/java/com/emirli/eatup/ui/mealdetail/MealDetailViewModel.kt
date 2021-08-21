package com.emirli.eatup.ui.mealdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.emirli.eatup.model.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
): ViewModel() {

    fun getMealById(mealId : Int) = apiRepository.getMealById(mealId)
}