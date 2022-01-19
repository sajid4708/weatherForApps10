package com.appstenx.appstenxweather.weather.model

import com.google.gson.annotations.SerializedName

data class ForeCastData (val day:String,val temperature:String )
data class currentTempData (
    @SerializedName("main"       ) var main       : Main?              = Main(),
    @SerializedName("name"       ) var name       : String?            = null,
    )
data class Main (

    @SerializedName("temp"       ) var temp      : Double? = null,
    @SerializedName("feels_like" ) var feelsLike : Double? = null,
    @SerializedName("temp_min"   ) var tempMin   : Double? = null,
    @SerializedName("temp_max"   ) var tempMax   : Double? = null,
    @SerializedName("pressure"   ) var pressure  : Int?    = null,
    @SerializedName("humidity"   ) var humidity  : Int?    = null

)