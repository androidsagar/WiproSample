package com.example.wiprosample.retrofit

sealed class ResponseWrapper<out T>(
    val feedList: T?=null
) {
    object Loading : ResponseWrapper<Nothing>()
    data class Error(val msg: String) : ResponseWrapper<Nothing>()
    data class Success<T>(val data: T) : ResponseWrapper<T>(data)
}