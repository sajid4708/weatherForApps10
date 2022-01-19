package com.appstenx.appstenxweather.weather.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper

import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.appstenx.appstenxweather.R
import com.appstenx.appstenxweather.common.BaseActivity
import com.appstenx.appstenxweather.databinding.ActivityWeatherBinding

import com.appstenx.appstenxweather.weather.view.ForecastAdapter
import com.appstenx.appstenxweather.weather.viewmodel.WeatherViewModel
import com.appstenx.appstenxweather.weather.viewmodel.WeatherViewModelFactory


class WeatherActivity : BaseActivity() {
    private lateinit var binding: ActivityWeatherBinding
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        dismissKeyBoard()
        val recykyAdapter = ForecastAdapter();
        findViewById<RecyclerView>(R.id.forecast_recyler).adapter = recykyAdapter;
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.line_seperator)!!)
        findViewById<RecyclerView>(R.id.forecast_recyler).addItemDecoration(divider)
        val loaderDialog = openLoader()
        Handler(Looper.getMainLooper()).postDelayed({
            runOnUiThread {
                loaderDialog.cancel()
            }
        }, 4000)
    }

    private fun initViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather)
        binding.lifecycleOwner = this
        weatherViewModel =
            ViewModelProvider(this, WeatherViewModelFactory()).get(WeatherViewModel::class.java)
        binding.weatherViewModel=weatherViewModel
    }
}
