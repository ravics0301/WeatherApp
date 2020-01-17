package com.example.weatherapplication.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapplication.MyApplication
import org.json.JSONObject
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.IOException

//typealias CountryList  = ArrayList<String>
typealias  CountryCityPair = Pair<MutableList<String>,HashMap<String,ArrayList<String>>>
class CountryProvider :KoinComponent {

     private val context:Application by inject()

    private val countryCityPair by lazy { loadJSONFromAsset() }

  private  fun loadJSONFromAsset(): CountryCityPair? {
        var json: String? = ""
        try {
            val values = context.assets.open("Country_State.json")
            json = values.bufferedReader().use{it.readText()}
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return pasrseJson(JSONObject(json))
    }
    private val _countryList = MutableLiveData< MutableList<String>>()
    private val countryList:LiveData<MutableList<String>> = _countryList

    fun getCountryList(filterdString:String): LiveData<MutableList<String>> {
        _countryList.value = countryCityPair?.first?.filter { it.contains(filterdString,true) }?.toMutableList()
        return countryList
    }

    fun pasrseJson(jsonObject: JSONObject):CountryCityPair{
        val countryArrayList:ArrayList<String> = ArrayList()
        val cityList:HashMap<String, ArrayList<String>> = HashMap()
        jsonObject.keys().forEach {
            countryArrayList.add(it)
            val cityArrays = jsonObject.getJSONArray(it).let {
                val temp = arrayListOf<String>()
                for(i in 0 until it.length()){
                  temp.add(it[i] as String)
                }
                temp
            }
            cityList[it] = cityArrays
        }
        return Pair(countryArrayList,cityList)
    }

    private val _cityList = MutableLiveData< MutableList<String>>()
    private val cityList:LiveData<MutableList<String>> = _cityList
    fun getCityList(countryName:String):
        LiveData<MutableList<String>> {
        _cityList.value = countryCityPair?.second?.get(countryName)
            return cityList
    }


}