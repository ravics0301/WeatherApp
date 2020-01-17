package com.example.weatherapplication.di

import android.app.Application
import com.example.weatherapplication.repository.CountryProvider
import com.example.weatherapplication.repository.WeatherProvider
import com.example.weatherapplication.view_model.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { WeatherViewModel() }
}

val reposModule = module {
    single { CountryProvider() }
    single { WeatherProvider() }
}

