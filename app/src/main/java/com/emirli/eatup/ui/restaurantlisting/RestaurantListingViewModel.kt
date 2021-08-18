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
class RestaurantListingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    apiRepository: ApiRepository
) : ViewModel() {

    val restaurantList: MutableLiveData<List<Restaurant>> = MutableLiveData()

    init {
        restaurantList.value = GsonBuilder().create().fromJson("""[
    {
        "id" : 1,
        "name": "BK",
        "detail" : "BK burgegr satar",
        "phone" :"5455",
        "cuisine" : "FastFood",
        "imageUrl" : "http://c1",
        "meals" :
        [
            {
                "id" : 1,
                "imageUrl" : "http://c1",
                "name" : "Broş",
                "price" : 32.99,
                "calorie" : 500,
                "vote" : 3.4,
                "quantity" : 120
            },
            {
                "id" : 1,
                "imageUrl" : "http://c1",
                "name" : "Wrapper",
                 "price" : 42.99,
                "calorie" : 800,
                "vote" : 1.4,
                "quantity" : 180
            }
            
        ],
        "deliveryTime" : "20 - 30 min",
        "minPrice" : "30",
        "vote" : "1.8"
    },
    {
        "id" : 2,
        "name": "Bursa Kebab",
        "detail" : "Bursaaar",
        "phone" :"5455",
        "cuisine" : "Turkish",
        "imageUrl" : "http://c1",
        "meals" :
        [
        
            {
                "id" : 1,
                "imageUrl" : "http://c1",
                "name" : "Kebab",
                 "price" : 22.99,
                "calorie" : 200,
                "vote" : 5.4,
                "quantity" : 80
            },
            {
                "id" : 2,
                "imageUrl" : "http://c1",
                "name" : "Döner",
                "price" : 82.99,
                "calorie" : 600,
                "vote" : 4.4,
                "quantity" : 280
            }
            
        ],
        "deliveryTime" : "20 - 30 min",
        "minPrice" : "100",
        "vote" : "1.8"
    }    
]
""", Array<Restaurant>::class.java).toList()
    }
}