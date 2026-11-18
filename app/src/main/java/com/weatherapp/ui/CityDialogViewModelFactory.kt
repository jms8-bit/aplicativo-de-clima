package com.weatherapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.weatherapp.api.WeatherService

class CityDialogViewModelFactory(
    private val service: WeatherService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityDialogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CityDialogViewModel(service) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}