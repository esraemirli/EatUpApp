package com.emirli.eatup.ui.entry.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.emirli.eatup.model.ApiRepository
import com.emirli.eatup.model.entity.login.LoginResponse
import com.emirli.eatup.model.entity.login.RegisterRequest
import com.emirli.eatup.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel()  {

    fun signup(request: RegisterRequest): LiveData<Resource<LoginResponse>> =
        apiRepository.register(request)
}