package com.appstenx.appstenxweather.weather.view

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration


import androidx.recyclerview.widget.RecyclerView
import com.appstenx.appstenxweather.BR

import com.appstenx.appstenxweather.R
import com.appstenx.appstenxweather.common.Fonts
import com.appstenx.appstenxweather.databinding.ForecastItemBinding
import com.appstenx.appstenxweather.weather.model.ForecastTempDataWithDay
import com.appstenx.appstenxweather.weather.model.ListForecastTempDataWithDay

class ForecastAdapter(var list: MutableList<ForecastTempDataWithDay>) :
    RecyclerView.Adapter<ForeCastViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForeCastViewHolder {
        val binding = DataBindingUtil.inflate<ForecastItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.forecast_item, parent, false
        )
        binding.temp.typeface = Fonts.robotoRegular
        binding.day.typeface = Fonts.robotoRegular
        return ForeCastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForeCastViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class ForeCastViewHolder(private val binding: ForecastItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(forecastTempDataWithDay: ForecastTempDataWithDay) {
        binding.setVariable(BR.itemForcast, forecastTempDataWithDay)
        binding.executePendingBindings()
    }

}

@BindingAdapter("forecastListAdapter")
fun bindForecastListAdapter(
    recyclerView: RecyclerView,
    list: ListForecastTempDataWithDay?
): ForecastAdapter {
    val bindableAdapter = ForecastAdapter(list?.listForecastedData ?: mutableListOf())
    recyclerView.adapter = bindableAdapter
    val divider = DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
    divider.setDrawable(
        ContextCompat.getDrawable(
            recyclerView.context, R.drawable.line_seperator
        )!!
    )
    recyclerView.addItemDecoration(divider)
    return bindableAdapter
}