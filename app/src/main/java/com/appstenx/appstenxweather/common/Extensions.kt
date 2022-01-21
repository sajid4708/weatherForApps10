package com.appstenx.appstenxweather.common

const val KelvinConstant=273.15
fun Double?.convertToCelsius() =
    if (this != null) (this- KelvinConstant).toInt().toString() + "°" else ""


fun Double?.convertToCelsiusWithC() =
    if (this != null) (this- KelvinConstant).toInt().toString() + " C" else ""
