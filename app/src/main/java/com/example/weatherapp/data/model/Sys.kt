package com.example.weatherapp.data.model

data class Sys(
    var type: Long? = null,
    var id: Long? = null,
    var message: Double? = null,
    var country: String? = null,
    var sunrise: Long? = null,
    var sunset: Long? = null
)