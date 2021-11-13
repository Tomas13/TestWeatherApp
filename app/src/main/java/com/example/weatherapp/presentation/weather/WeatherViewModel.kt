package com.example.weatherapp.presentation.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.domain.WeatherUseCase
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherUseCase: WeatherUseCase
) : ViewModel() {

    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather> = _weather

    fun getWeatherFor(cityName: String) {
        viewModelScope.launch {
            _weather.value = weatherUseCase(cityName).body()
        }
    }
}