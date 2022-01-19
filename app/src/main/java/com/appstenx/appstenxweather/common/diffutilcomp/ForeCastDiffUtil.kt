package com.appstenx.appstenxweather.common.diffutilcomp

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.appstenx.appstenxweather.weather.model.ForeCastData

class ForeCastDiffUtil(private val oldList: List<ForeCastData>, private val newList: List<ForeCastData>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return return oldList[oldItemPosition].id === newList.get(newItemPosition).id
    }


    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {


        return oldList[oldPosition].equals(newList[newPosition])
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}