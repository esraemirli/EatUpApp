package com.emirli.eatup.model.local

import javax.inject.Inject

class LocalDataSource @Inject constructor(private val sharedPrefManager: SharedPrefManager) {

    fun saveToken(token: String) {
        sharedPrefManager.saveToken(token)
    }

    fun getToken(): String? {
        return sharedPrefManager.getToken()
    }

    fun saveImage(image: String) {
        sharedPrefManager.saveImage(image)
    }

    fun getImage(): String? {
        return sharedPrefManager.getImage()
    }

}