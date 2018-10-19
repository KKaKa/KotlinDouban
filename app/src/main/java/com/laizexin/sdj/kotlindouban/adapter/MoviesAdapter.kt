package com.laizexin.sdj.kotlindouban.adapter

import android.content.Context
import com.laizexin.sdj.kotlindouban.R
import com.laizexin.sdj.kotlindouban.bean.Movie
import com.laizexin.sdj.kotlindouban.recycleview.BaseViewHolder
import com.laizexin.sdj.kotlindouban.recycleview.SingleItemTypeAdapter

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/9/29
 */
class MoviesAdapter(context: Context?, layoutId: Int, datas: List<Movie.SubjectsBean>?) : SingleItemTypeAdapter<Movie.SubjectsBean>(context, layoutId, datas) {
    private var listener : OnHolderClickListener? = null

    override fun convert(holder: BaseViewHolder?, t: Movie.SubjectsBean?, position: Int) {
        val stringbuilder = StringBuilder("导演：")
        holder!!.setText(R.id.tv_movie_title,datas.get(position).title)
        holder.setRating(R.id.rating_bar,datas.get(position).rating!!.stars!!.toFloat().div(10))
        holder.setImageUrl(R.id.image_movie,datas.get(position).images!!.medium)
        holder.setText(R.id.tv_rating, datas.get(position).rating!!.average.toString())
        for(i in 0 until datas.get(position).directors!!.size){
            stringbuilder.append(datas.get(position).directors!![i].name).append("/")
        }
        holder.setText(R.id.tv_directors,stringbuilder.toString())
        stringbuilder.delete(0,stringbuilder.length).append("主演：")
        for(i in 0 until datas.get(position).casts!!.size){
            stringbuilder.append(datas.get(position).casts!![i].name).append("/")
        }
        holder.setText(R.id.tv_casts,stringbuilder.toString())
        holder.setText(R.id.tv_year,"年份："+datas.get(position).year)
        holder.setOnclickListener(R.id.root_view) {
            if(listener != null)
                listener!!.onHolderClick(datas.get(position).id!!.toInt())
        }
    }

    public interface OnHolderClickListener{
        fun onHolderClick(id:Int)
    }

    fun setOnHolderClickListener(listener: OnHolderClickListener){
        this.listener = listener
    }
}