package com.example.weatherapp.data.model

data class Weather(
    var coord: Coord? = null,
    var weather: List<Weather_>? = null,
    var base: String? = null,
    var main: Main? = null,
    var visibility: Long? = null,
    var wind: Wind? = null,
    var clouds: Clouds? = null,
    var dt: Long? = null,
    var sys: Sys? = null,
    var id: Long? = null,
    var name: String? = null,
    var cod: Long? = null
)