package com.laizexin.sdj.kotlindouban.adapter

import android.content.Context
import android.view.View
import com.laizexin.sdj.kotlindouban.R
import com.laizexin.sdj.kotlindouban.bean.Actor
import com.laizexin.sdj.kotlindouban.recycleview.BaseViewHolder
import com.laizexin.sdj.kotlindouban.recycleview.SingleItemTypeAdapter
import com.laizexin.sdj.kotlindouban.ui.CelebrityActivity

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/12
 */
class ActorAdapter(context: Context?, layoutId: Int, datas: List<Actor>?) : SingleItemTypeAdapter<Actor>(context, layoutId, datas) {

    override fun convert(holder: BaseViewHolder?, t: Actor?, position: Int) {
        holder!!.setImageUrl(R.id.image_actor,datas.get(position).imageUrl)
        holder.setText(R.id.tv_actor_name,datas.get(position).name)
        holder.setOnclickListener(R.id.root_view, View.OnClickListener { it ->
            datas.get(position).id?.let {
                CelebrityActivity.toCelebrityActivity(mContext,datas.get(position).id!!.toInt())
            }
        })
    }
}