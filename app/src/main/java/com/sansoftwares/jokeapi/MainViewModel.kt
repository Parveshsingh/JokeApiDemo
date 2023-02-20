package com.sansoftwares.jokeapi

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor()  : ViewModel() {

    val jokelist = MutableLiveData<List<String>>()
    val alljokelist = MutableLiveData<List<String>>()
    val errorMessage = MutableLiveData<String>()
    var data : ArrayList<String> = ArrayList()

     fun getData()
    {
        RetrofitInstance.apiInterFace.getData("json").enqueue(object : Callback<MainResponse> {
            override fun onResponse(call: Call<MainResponse>, response: Response<MainResponse>) {
                Log.d("Response","The Response is ${response.body()!!.joke}")
                alljokelist.value = data
                if(data.size >= 10)
                {
                    data.removeAt(0)
                    data.add(response.body()!!.joke)
                    jokelist.value = data
                }else
                {
                    data.add(response.body()!!.joke)
                    jokelist.value = data
                }

            }

            override fun onFailure(call: Call<MainResponse>, t: Throwable) {
                Log.d("Failure","The Response is ${t.message}");
            }

        })
    }
}