package com.appstenx.appstenxweather.common

import android.app.Application
import android.graphics.Typeface
import com.appstenx.appstenxweather.common.Fonts.robotoBlack
import com.appstenx.appstenxweather.common.Fonts.robotoRegular
import com.appstenx.appstenxweather.common.Fonts.robotoThin
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initFonts()
    }
    companion object{
   
    }

   private fun initFonts(){
        robotoBlack=Typeface.createFromAsset(assets, "Roboto-Black.ttf")
        robotoRegular=Typeface.createFromAsset(assets, "Roboto-Regular.ttf")
       robotoThin=Typeface.createFromAsset(assets, "Roboto-Thin.ttf");
    }
}

object Fonts {
    var robotoBlack:Typeface?=null
    var robotoRegular:Typeface?=null
    var robotoThin:Typeface?=null
}