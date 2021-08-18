package com.emirli.eatup.ui.restaurantdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.emirli.eatup.model.ApiRepository
import com.emirli.eatup.model.entity.Restaurant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class RestaurantViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    apiRepository: ApiRepository
) : ViewModel() {
    private val restaurantList: MutableLiveData<List<Restaurant>> = MutableLiveData()

    init {
        val typeToken = object : TypeToken<List<Restaurant>>() {}.type
        restaurantList.value = Gson().fromJson<List<Restaurant>>(restaurantJson, typeToken)
    }

}

val restaurantJson = """
       [

    {
        "id" : 1,
        "name": "BK",
        "detail" : "BK burgegr satar",
        "phone" :"5455",
        "imageUrl" : "http://c1",
        "meals" :
        [
        
            {
                "id" : 1,
                "imageUrl" : "http://c1",
                "name" : "Izgara"
            },
            {
                "id" : 1,
                "imageUrl" : "http://c1",
                "name" : "Izgara"
            }
            
        ],
        "deliveryTime" : "20 - 30 dk",
        "minPrice" : "30TL"
    },
    
    
    {
        "id" : 2,
        "name": "Bursa Kebab",
        "detail" : "Bursaaar",
        "phone" :"5455",
        "imageUrl" : "http://c1",
        "meals" :
        [
        
            {
                "id" : 1,
                "imageUrl" : "http://c1",
                "name" : "Kebab"
            },
            {
                "id" : 2,
                "imageUrl" : "http://c1",
                "name" : "Kebab"
            }
            
        ],
        "deliveryTime" : "20 - 30 dk",
        "minPrice" : "100TL"
    }
    
]
""".trimIndent()