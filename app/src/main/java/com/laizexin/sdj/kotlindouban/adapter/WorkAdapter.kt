package com.laizexin.sdj.kotlindouban.adapter

import android.content.Context
import android.view.View
import com.laizexin.sdj.kotlindouban.R
import com.laizexin.sdj.kotlindouban.bean.Celebrity
import com.laizexin.sdj.kotlindouban.recycleview.BaseViewHolder
import com.laizexin.sdj.kotlindouban.recycleview.SingleItemTypeAdapter
import com.laizexin.sdj.kotlindouban.ui.MovieDetailActivity

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/16
 */
class WorkAdapter(context: Context?, layoutId: Int, datas: List<Celebrity.WorksBean>?) : SingleItemTypeAdapter<Celebrity.WorksBean>(context, layoutId, datas) {

    override fun convert(holder: BaseViewHolder?, t: Celebrity.WorksBean?, position: Int) {
        holder!!.setImageUrl(R.id.image_movie,datas[position].subject!!.images!!.medium)
        holder.setText(R.id.tv_movie_title,datas[position].subject!!.title)
        holder.setRating(R.id.rating_bar,datas.get(position).subject!!.rating!!.stars!!.toFloat().div(10))
        holder.setOnclickListener(R.id.root_view, View.OnClickListener {
            MovieDetailActivity.toMovieDetailActivity(mContext,datas[position].subject!!.id!!.toInt())
        })
    }
}