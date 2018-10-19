package com.laizexin.sdj.kotlindouban.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.laizexin.sdj.kotlindouban.R

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/8
 */
class SettingAdapter(context : Context,data : MutableList<String>) : RecyclerView.Adapter<SettingAdapter.MySettingViewHolder>() {

    private var mContext : Context
    private var mData : MutableList<String>

    init {
        this.mContext = context
        this.mData = data
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MySettingViewHolder {
        val v = LayoutInflater.from(parent!!.context).inflate(R.layout.item_setting,parent,false)
        return MySettingViewHolder(v)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: MySettingViewHolder?, position: Int) {
        holder!!.textView.text = mData.get(position)
    }

    class MySettingViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView!!.findViewById<TextView>(R.id.tv)!!
    }
}