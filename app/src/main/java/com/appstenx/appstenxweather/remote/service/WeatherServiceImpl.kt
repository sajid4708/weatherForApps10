package com.appstenx.appstenxweather.remote.service

import javax.inject.Inject

class WeatherServiceImpl @Inject constructor(private val weatherService: WeatherService) {
    suspend fun getCurrentWeatherData() = weatherService.getCurrentWeather()
    suspend fun getForeCastList()=weatherService.getForecastData()
}