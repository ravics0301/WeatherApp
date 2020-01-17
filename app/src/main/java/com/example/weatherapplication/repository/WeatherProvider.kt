package com.example.weatherapplication.repository

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.weatherapplication.R
import com.example.weatherapplication.WEATHER_API_KEY
import com.example.weatherapplication.model.WeatherResponse
import com.example.weatherapplication.retrofit.ResponseWrapper
import com.example.weatherapplication.retrofit.Weather
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class WeatherProvider : KoinComponent {
    private val weatherRequest: Weather by inject()
    private val context: Application by inject()

    @SuppressLint("CheckResult")
    fun fetchWeather(city_country: String, response: (ResponseWrapper<WeatherResponse>) -> Unit) {
        weatherRequest.fetchWeather(city_country, WEATHER_API_KEY)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { response(ResponseWrapper.Loading) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                response(ResponseWrapper.Success(it))
            }, {
                it.printStackTrace()
                response(ResponseWrapper.Error(it.message ?: context.getString(R.string.something_went_wrong)))
            })
    }
}