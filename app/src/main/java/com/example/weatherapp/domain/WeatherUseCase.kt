package com.example.weatherapp.domain

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.Repository
import com.example.weatherapp.data.model.Weather
import retrofit2.Call
import retrofit2.Response

class WeatherUseCase(private val repository: Repository) {
    suspend operator fun invoke(cityName: String): Response<Weather> {
        return repository.getWeather(
            cityName = cityName,
        )
    }
}