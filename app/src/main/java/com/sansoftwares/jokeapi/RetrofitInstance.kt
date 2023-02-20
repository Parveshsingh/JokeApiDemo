package com.sansoftwares.jokeapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://geek-jokes.sameerkumar.website/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiInterFace by lazy {
        retrofit.create(ApiInterFace::class.java)
    }
}
