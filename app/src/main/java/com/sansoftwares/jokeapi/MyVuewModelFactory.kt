package com.sansoftwares.jokeapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyViewModelFactory constructor(): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel() as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}