package com.example.weatherapp.data.network

import com.example.weatherapp.data.model.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") cityName: String?,
        //use api key here for testing purposes
        @Query("appid") appId: String? = "784aa9d39d19375f419f7467eff10c01",
        @Query("units") units: String? = "metric"
    ): Response<Weather>
}


sealed class ApiResponse<T> {
    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body)
                }
            } else {
                ApiErrorResponse(
                    response.code(),
                    response.errorBody()?.string() ?: response.message()
                )
            }
        }

        fun <T> create(errorCode: Int, error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(errorCode, error.message ?: "Unknown Error!")
        }
    }
}

class ApiEmptyResponse<T> : ApiResponse<T>()
data class ApiErrorResponse<T>(val errorCode: Int, val errorMessage: String) : ApiResponse<T>()
data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()