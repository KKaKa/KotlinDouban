package com.laizexin.sdj.kotlindouban.utils

import android.content.Context
import android.widget.ImageView
import com.laizexin.sdj.kotlindouban.R
import com.squareup.picasso.Picasso

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/11
 */

fun ImageView.loadImgUrl(mContext: Context, imgUrl : String){
    Picasso.with(mContext)
            .load(imgUrl)
            .error(R.mipmap.blank_img)
            .placeholder(R.mipmap.blank_img)
            .centerCrop()
            .fit()
            .into(this)
}