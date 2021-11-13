package com.example.weatherapp.data.model

import com.example.weatherapp.R

data class Weather_(
    var id: Long? = null,
    var main: String? = null,
    var description: String? = null,
    var icon: String? = null
) {

    fun getDescriptionDrawable(): Int {
        return when (description) {
            "clear sky" -> R.drawable.sunny
            "scattered clouds" -> R.drawable.cloudy
            "broken clouds" -> R.drawable.cloudy
            "overcast clouds" -> R.drawable.cloudy
            "light intensity shower rain" -> R.drawable.rainy
            "shower rain" -> R.drawable.rainy
            "few clouds" -> R.drawable.cloudy
            else -> R.drawable.sunny
        }
    }

}