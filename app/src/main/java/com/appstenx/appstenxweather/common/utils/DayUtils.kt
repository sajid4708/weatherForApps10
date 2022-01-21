package com.appstenx.appstenxweather.common.utils

import java.text.SimpleDateFormat
import java.util.*

object DayUtils {
    fun getDayFromFormattedString(formattedDate: String): String {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date= format.parse(formattedDate)
        return SimpleDateFormat("EEEE", Locale.ENGLISH).format(date)
    }

    fun getDayFromTimeInMilliseconds(milliseconds: Long): String {
        val date = Date(milliseconds)
        return SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.time)
    }
}