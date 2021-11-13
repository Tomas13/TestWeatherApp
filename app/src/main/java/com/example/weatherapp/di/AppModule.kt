package com.example.weatherapp.di

import com.example.weatherapp.domain.WeatherUseCase
import com.example.weatherapp.presentation.weather.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { WeatherViewModel(get()) }
}

val appModule = module {
    single { WeatherUseCase(get()) }
}


