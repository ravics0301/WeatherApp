package com.example.weatherapplication.view_model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapplication.model.WeatherResponse
import com.example.weatherapplication.repository.CountryProvider
import com.example.weatherapplication.repository.WeatherProvider
import com.example.weatherapplication.retrofit.ResponseWrapper
import org.koin.core.KoinComponent
import org.koin.core.inject

class WeatherViewModel :ViewModel(),KoinComponent {

    private val countryProvider:CountryProvider by inject()
    private val weatherProvider:WeatherProvider by inject()

    var cityData = MutableLiveData<ResponseWrapper<WeatherResponse>>()


    fun getCountryDetail(filtered:String): LiveData<MutableList<String>> {
        return countryProvider.getCountryList(filtered)

    }
    fun getCityDetail(countryName:String): LiveData<MutableList<String>> {
       return countryProvider.getCityList(countryName)


    }
    fun getWeatherInformation(city:String,countryName: String,blankField: (Boolean) -> Unit){
        if(validation(city,countryName))
        {
            weatherProvider.fetchWeather("$city,$countryName"){ cityData.value = it }
        }
        else{
            blankField.invoke(false)
        }
    }

    fun validation(city:String,countryName: String):Boolean{
        return city.isNotEmpty() && countryName.isNotEmpty()
    }

}