package com.appstenx.appstenxweather

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SlidingDrawer
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.appstenx.appstenxweather.common.BaseActivity
import com.appstenx.appstenxweather.weather.model.ForeCastData
import com.appstenx.appstenxweather.weather.view.ForecastAdapter

class WeatherActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dismissKeyBoard()
        setContentView(R.layout.activity_main)
        val recykyAdapter = ForecastAdapter();
        findViewById<RecyclerView>(R.id.forecast_recyler).adapter = recykyAdapter;
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.line_seperator)!!)
        findViewById<RecyclerView>(R.id.forecast_recyler).addItemDecoration(divider)
        val loaderDialog=openLoader()
        Handler(Looper.getMainLooper()).postDelayed({runOnUiThread{
            loaderDialog.cancel()
        }},4000)
    }
}