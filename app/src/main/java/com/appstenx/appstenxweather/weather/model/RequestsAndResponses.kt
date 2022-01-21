package com.appstenx.appstenxweather.weather.model

import com.google.gson.annotations.SerializedName



data class CurrentTempData (
    @SerializedName("main"       ) val main       : Main?              = Main(),
    @SerializedName("name"       ) val name       : String?            = null,
    )
data class Main(
    @SerializedName("temp"       ) var temp      : Double? = null,
)

data class ListForecastTempDataWithDay(val listForecastedData: MutableList<ForecastTempDataWithDay> = mutableListOf())

data class ForecastTempDataWithDay(
     val temp      : String              = "",
    val day       : String ="",
)
data class ForecastTempDataWithTime(
    @SerializedName("main"       ) val main       : Main?              = Main(),
    @SerializedName("dt_txt"       ) val formattedDtText       : String ="" ,
)
data class ForeCastList (

    @SerializedName("cod"     ) var cod     : String?         = null,
    @SerializedName("message" ) var message : Int?            = null,
    @SerializedName("list"    ) var list    : MutableList<ForecastTempDataWithTime> = mutableListOf(),


    )