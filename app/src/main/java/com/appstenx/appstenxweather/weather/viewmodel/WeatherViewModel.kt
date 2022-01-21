package com.appstenx.appstenxweather.weather.viewmodel

import androidx.lifecycle.*
import com.appstenx.appstenxweather.common.convertToCelsius
import com.appstenx.appstenxweather.common.utils.CircularDays
import com.appstenx.appstenxweather.common.utils.DayUtils
import com.appstenx.appstenxweather.remote.ApiState
import com.appstenx.appstenxweather.remote.repo.WeatherRepository
import com.appstenx.appstenxweather.weather.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {
    private val currentWeatherStateFlow: MutableStateFlow<ApiState> =
        MutableStateFlow(ApiState.Empty)
    val _currentWeatherPostStateFlow: StateFlow<ApiState> = currentWeatherStateFlow
    private val forecastStateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val _forecastWeatherPostStateFlow: StateFlow<ApiState> = forecastStateFlow
    private val _currentWeatherLiveData: MutableLiveData<CurrentTempData> by lazy {
        MutableLiveData<CurrentTempData>()
    }
    val currentWeatherLiveData: LiveData<CurrentTempData>
        get() = _currentWeatherLiveData

    val currentTempTransform = Transformations.map(currentWeatherLiveData) {
        it.main?.temp.convertToCelsius()
    }

    private val _avgForecastLiveData: MutableLiveData<ListForecastTempDataWithDay> by lazy {
        MutableLiveData<ListForecastTempDataWithDay>()
    }
    val avgForecastLiveData: MutableLiveData<ListForecastTempDataWithDay>
        get() = _avgForecastLiveData



    fun getCurrentWeatherData() = viewModelScope.launch {
        currentWeatherStateFlow.value = ApiState.Loading
        weatherRepository.getCurrentWeatherFromRemote()
            .catch { e ->
                currentWeatherStateFlow.value = ApiState.Failure(e)
            }.collect { data ->
                currentWeatherStateFlow.value = ApiState.Success(data)

                _currentWeatherLiveData.value = data
            }
    }

    fun getForeCastData() = viewModelScope.launch {
        forecastStateFlow.value = ApiState.Loading
        weatherRepository.getForeCastDataFromRemote()
            .catch { e ->
                forecastStateFlow.value = ApiState.Failure(e)
            }.collect { data ->
                val avgList=getListOfAverageForeCast(data)
                forecastStateFlow.value = ApiState.Success(avgList)
                _avgForecastLiveData.value= ListForecastTempDataWithDay(avgList.toMutableList())
            }
    }

    private fun getListOfAverageForeCast(data:ForeCastList):List<ForecastTempDataWithDay>{
        var weekCircular = CircularDays.getCircularDays()
        while (!weekCircular.data.equals(DayUtils.getDayFromTimeInMilliseconds(System.currentTimeMillis()))) {
            weekCircular = weekCircular.next!!
        }
        val forecastWithTempAndDay = mutableListOf<ForecastTempDataWithDay>()
        for (i in 1..4) {
            val avgTempDay = data.list.filter {
                DayUtils.getDayFromFormattedString(it.formattedDtText).equals(weekCircular.next!!.data)
            }
                .map { it.main?.temp?:0.0 }
                .average().toInt()

            forecastWithTempAndDay.add(
                ForecastTempDataWithDay(
                    avgTempDay.convertToCelsius(),
                    weekCircular.next!!.data
                )
            )

            weekCircular = weekCircular.next!!
        }
        return forecastWithTempAndDay;
    }




}