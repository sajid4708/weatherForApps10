package com.appstenx.appstenxweather.remote.repo

import com.appstenx.appstenxweather.remote.service.WeatherServiceImpl
import com.appstenx.appstenxweather.weather.model.CurrentTempData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherServiceImpl: WeatherServiceImpl) {
    fun getCurrentWeatherFromRemote()=flow{
        emit(weatherServiceImpl.getCurrentWeatherData())
    }.flowOn(Dispatchers.IO)

    fun getForeCastDataFromRemote()=flow{
        emit(weatherServiceImpl.getForeCastList())
    }.flowOn(Dispatchers.IO)
}