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
class CoorAdapter : RecyclerView.Adapter<CoorAdapter.MyViewHolder> {

    private var mContext: Context? = null
    private var mDatas: List<String>? = null

    constructor(mContext: Context, mDatas: List<String>) {
        this.mContext = mContext
        this.mDatas = mDatas
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_setting, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = mDatas!![position]
    }

    override fun getItemCount(): Int {
        return mDatas!!.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView

        init {
            textView = itemView.findViewById<View>(R.id.tv) as TextView
        }
    }

}
