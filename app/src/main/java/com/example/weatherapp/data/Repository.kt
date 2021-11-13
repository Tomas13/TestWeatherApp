package com.example.weatherapp.data

import com.example.weatherapp.data.model.Weather
import retrofit2.Response

interface Repository {

    suspend fun getWeather(
        cityName: String?
    ): Response<Weather>

}