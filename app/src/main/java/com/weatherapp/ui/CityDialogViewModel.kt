package com.weatherapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.weatherapp.api.WeatherService
import com.weatherapp.model.City

class CityDialogViewModel(private val service: WeatherService) : ViewModel() {
    var query by mutableStateOf("")
    var suggestions by mutableStateOf<List<City>>(emptyList())
        private set
    var loading by mutableStateOf(false)
        private set

    fun onQueryChanged(value: String) {
        query = value
        service.cancelAll()
        if (value.length < 3) {
            loading = false
            suggestions = emptyList()
        } else {
            loading = true
            service.search(value) { res ->
                // cancelAll() dispara onFailure com null; ignora pra não piscar o loading
                if (res != null) {
                    suggestions = res.mapNotNull {
                        val n = it.name
                        val la = it.lat
                        val lo = it.lon
                        if (n != null && la != null && lo != null)
                            City(n, location = LatLng(la, lo)) else null
                    }
                    loading = false
                }
            }
        }
    }
}