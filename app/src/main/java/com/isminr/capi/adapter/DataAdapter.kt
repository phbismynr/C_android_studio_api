package com.isminr.capi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.isminr.capi.R
import com.isminr.capi.model.DataItem
import kotlinx.android.synthetic.main.item_data.view.*

class DataAdapter (val data: List<DataItem>? , private val click: onClickItem) : RecyclerView.Adapter<DataAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data,parent,false)
        return MyHolder(view)
    }

    override fun getItemCount() = data?.size ?: 0

    override fun onBindViewHolder(holder: MyHolder, position: Int)
    {
        holder.onBind(data?.get(position))
    }

    class MyHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun onBind(get: DataItem?) {
            itemView.tvName.text = get?.staffName
            itemView.tvPhone.text = get?.staffHp
            itemView.tvAddress.text = get?.staffAlamat
        }
    }

    interface onClickItem{
        fun clicked (item: DataItem?)
        fun delete(item: DataItem?)

    }
}