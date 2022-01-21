package com.appstenx.appstenxweather.weather.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels

import androidx.lifecycle.lifecycleScope

import com.appstenx.appstenxweather.common.BaseActivity
import com.appstenx.appstenxweather.common.Fonts
import com.appstenx.appstenxweather.databinding.ActivityWeatherBinding
import com.appstenx.appstenxweather.remote.ApiState

import com.appstenx.appstenxweather.weather.viewmodel.WeatherViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class WeatherActivity : BaseActivity() {
    private lateinit var binding:ActivityWeatherBinding
    private val weatherViewModel: WeatherViewModel by viewModels<WeatherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFonts()
        dismissKeyBoard()
        binding.lifecycleOwner=this
        binding.weatherViewModel=weatherViewModel
        getWeatherDataFromRemote()
        binding.errorView.retryBtn.setOnClickListener {
           binding.errorView.root.visibility=View.GONE
            getWeatherDataFromRemote()
        }

    }

    private fun setFonts(){
        binding.currentWeather.temperature.setTypeface(Fonts.robotoBlack)
        binding.currentWeather.city.setTypeface(Fonts.robotoThin)
        binding.errorView.textView.setTypeface(Fonts.robotoThin)
    }

    private fun getWeatherDataFromRemote(){
        weatherViewModel.getCurrentWeatherData()

        lifecycleScope.launchWhenStarted {
            weatherViewModel._currentWeatherPostStateFlow.collect {
                when(it){
                    is ApiState.Success<*> ->{
                        closeLoader()
                    }
                    is ApiState.Loading->{
                        openLoader()
                    }
                    is ApiState.Failure->{
                        closeLoader()
                        binding.errorView.root.visibility=View.VISIBLE

                    }
                }
            }}
            weatherViewModel.getForeCastData()
        lifecycleScope.launchWhenStarted {
            weatherViewModel._forecastWeatherPostStateFlow.collect {
                when(it){
                    is ApiState.Success<*> ->{
                        closeLoader()

                    }
                    is ApiState.Loading->{
                        openLoader()
                    }
                    is ApiState.Failure->{
                        closeLoader()
                        binding.errorView.root.visibility=View.VISIBLE

                    }
                }
            }
        }
        }

    }



