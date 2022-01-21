package com.appstenx.appstenxweather.common

fun Double?.convertToCelsius() =
    if (this != null) this.toInt().toString() + "°" else ""

fun Int.convertToCelsius() =
    if (this != null) this.toInt().toString() + " C" else ""