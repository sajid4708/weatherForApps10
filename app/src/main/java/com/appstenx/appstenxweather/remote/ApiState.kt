package com.appstenx.appstenxweather.remote

import com.appstenx.appstenxweather.weather.model.CurrentTempData

sealed class ApiState{
    object Loading:ApiState()
    class Failure(val msg:Throwable):ApiState()
    object Empty:ApiState()
    class Success<T>(val data:T):ApiState()
}


class GenericApiService{
    companion object{

    }
}