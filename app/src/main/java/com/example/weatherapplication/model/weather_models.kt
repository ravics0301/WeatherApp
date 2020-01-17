package com.example.weatherapplication.model

data class WeatherResponse(var coord:Coord,var weather:ArrayList<Weather>,var base:String = "",
                               var main:Main,var visibility:Int,var wind:Wind,var clouds:Cloud,var dt:Long,var sys:Sys,var id:Long,var name:String = "",var cod:Int)

data class Coord(var lon:String = "",var lat:String = "")

data class Weather(var id:Int = 0,var main:String = "",var description:String = "",var icon:String = "")
data class Main(var temp:String = "",var pressure:String = "",var humidity:String = "",var temp_min:String = "",var temp_max:String = "")
data class Wind(var speed:Float,var deg:Int )
data class Cloud(var all:Int)
data class Sys(var type:Int,var id:Long,var message:Float,var country:String = "",var sunrise:Long,var sunset:Long)



