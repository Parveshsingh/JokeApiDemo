package com.sansoftwares.jokeapi

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterFace {

    @GET("api")
    fun getData(@Query("format") format : String) : retrofit2.Call<MainResponse>
}