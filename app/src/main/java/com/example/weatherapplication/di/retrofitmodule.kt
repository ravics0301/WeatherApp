package com.example.weatherapplication.di

import com.example.weatherapplication.retrofit.Weather
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
}

val networkRequestModule = module {

    single {  provideWeatherRequest(get ())}
}
const val baseURL = "http://api.openweathermap.org/data/2.5/"

private fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(3, TimeUnit.MINUTES)
        .build()
}

private fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder().client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(baseURL)
        .build()
}

private fun provideWeatherRequest(retrofit: Retrofit)=retrofit.create(Weather::class.java)