package com.emirli.eatup.ui.network

import androidx.lifecycle.MutableLiveData
import com.emirli.eatup.model.remote.APIService
import javax.inject.Inject

class RetroRepository @Inject constructor(private val APIService: APIService) {

    fun makeApiCall(query:String, liveDataList: MutableLiveData<List<RecyclerData>>?){
//        val call: Call<RecyclerList> = APIService.login(query)
//        call?.enqueue(object: Callback<RecyclerList> {
//            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
//                print("ESRAA")
//            }
//
//            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
//                print("amk")
//            }
//        })
    }
}