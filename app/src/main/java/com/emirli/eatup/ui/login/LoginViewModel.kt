package com.emirli.eatup.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.emirli.eatup.model.ApiRepository
import com.emirli.eatup.model.entity.login.LoginRequest
import com.emirli.eatup.model.entity.login.LoginResponse
import com.emirli.eatup.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun login(): LiveData<Resource<LoginResponse>> {
        val request = LoginRequest("emirli","seyit")
        return apiRepository.login(request)
    }

}