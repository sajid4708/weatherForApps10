package com.appstenx.appstenxweather.remote.service

import retrofit2.Response

interface WeatherService {
    suspend fun getCurrentTemp(): Response<>
}