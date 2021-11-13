package com.example.weatherapp.di

import com.example.weatherapp.data.Repository
import com.example.weatherapp.data.RepositoryImpl
import com.example.weatherapp.data.network.NetworkService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "http://api.openweathermap.org/"
private const val TIME_OUT = 2L

val dataModule = module {
    single<Repository> { RepositoryImpl(get()) }

    single { createService(get()) }

    single { createRetrofit(get(), BASE_URL) }

    single { createOkHttpClient() }
}


fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun createService(retrofit: Retrofit): NetworkService {
    return retrofit.create(NetworkService::class.java)
}
