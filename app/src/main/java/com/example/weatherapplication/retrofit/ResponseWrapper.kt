package com.example.weatherapplication.retrofit

sealed class ResponseWrapper<out T>(
    val feedList: T?=null,
    val error_msg:String?=null
) {
    object Loading : ResponseWrapper<Nothing>()
    data class Error(val msg: String) : ResponseWrapper<Nothing>(error_msg = msg)
    data class Success<T>(val data: T) : ResponseWrapper<T>(data)
}