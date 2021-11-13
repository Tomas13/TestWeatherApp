package com.example.weatherapp.data

import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.data.network.NetworkService
import retrofit2.Response

class RepositoryImpl(private val networkService: NetworkService) : Repository {

    override suspend fun getWeather(
        cityName: String?
    ): Response<Weather> {
        return networkService.getWeather(cityName = cityName)
    }

}