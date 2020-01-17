package com.example.weatherapplication

import android.app.Application
import com.example.weatherapplication.di.networkRequestModule
import com.example.weatherapplication.di.reposModule
import com.example.weatherapplication.di.retrofitModule
import com.example.weatherapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication :Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(viewModelModule, reposModule,retrofitModule,networkRequestModule))
        }
    }
}