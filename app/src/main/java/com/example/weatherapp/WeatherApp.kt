package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.appModule
import com.example.weatherapp.di.dataModule
import com.example.weatherapp.di.viewModelModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .build()
        )

        startKoin {
            androidLogger()
            androidContext(this@WeatherApp)
            modules(
                listOf(
                    dataModule,
                    appModule,
                    viewModelModule
                )
            )
        }
    }

}

