package com.example.weatherapplication.retrofit

import com.example.weatherapplication.model.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Weather {

    @GET("weather")
     fun fetchWeather(@Query("q") q: String ,@Query("appid")appid:String,@Query("units")unit:String = "metric"): Observable<WeatherResponse>
}