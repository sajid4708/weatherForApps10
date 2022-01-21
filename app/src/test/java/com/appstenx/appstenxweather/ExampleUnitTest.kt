package com.appstenx.appstenxweather

import com.appstenx.appstenxweather.TestConstants.formattedTomorrowDate
import com.appstenx.appstenxweather.TestConstants.timeInMilliFromToday
import com.appstenx.appstenxweather.common.utils.DayUtils
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun isCorrectDayShownWithTimeInMilliSeconds() {
        assertEquals("Friday", DayUtils.getDayFromTimeInMilliseconds(timeInMilliFromToday))
    }



    @Test
    fun isCorrectDayShowFromFormattedDateAndTime(){
        assertEquals("Saturday", DayUtils.getDayFromFormattedString(formattedTomorrowDate))
    }


}

object TestConstants{
    const val timeInMilliFromToday=1642754296448
    const val formattedTomorrowDate="2022-01-22 00:00:00"

}