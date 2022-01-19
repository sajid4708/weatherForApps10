package com.appstenx.appstenxweather.weather.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.appstenx.appstenxweather.R
import com.appstenx.appstenxweather.common.diffutilcomp.ForeCastDiffUtil
import com.appstenx.appstenxweather.weather.model.ForeCastData

class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    private val foreCastDatas = ArrayList<ForeCastData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.forecast_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
holder.itemView.findViewById<TextView>(R.id.day).text="Tuesday"
        holder.itemView.findViewById<TextView>(R.id.temp).text="23 C"
    }

    fun setData(newForeCastDatas: List<ForeCastData>) {
        val diffCallback = ForeCastDiffUtil(foreCastDatas, newForeCastDatas)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        foreCastDatas.clear()
        foreCastDatas.addAll(newForeCastDatas)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return 10
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



    }
}