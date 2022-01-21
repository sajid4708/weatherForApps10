package com.appstenx.appstenxweather.remote.service

import com.appstenx.appstenxweather.weather.model.CurrentTempData
import com.appstenx.appstenxweather.weather.model.ForeCastList
import com.appstenx.appstenxweather.weather.model.ForecastTempDataWithTime
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/data/2.5/weather")
    suspend fun getCurrentWeather(@Query(value ="q")city:String="Bengaluru",@Query(value ="APPID")appId:String="9b8cb8c7f11c077f8c4e217974d9ee40",@Query(value = "units") temp:String="metric"): CurrentTempData

    @GET("/data/2.5/forecast")
     suspend fun getForecastData(@Query(value ="q")city:String="Bengaluru",@Query(value ="APPID")appId:String="9b8cb8c7f11c077f8c4e217974d9ee40",@Query(value = "units") temp:String="metric"):ForeCastList
}