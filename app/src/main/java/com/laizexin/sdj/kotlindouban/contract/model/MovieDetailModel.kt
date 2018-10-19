package com.laizexin.sdj.kotlindouban.contract.model

import com.laizexin.sdj.kotlindouban.contract.IModel
import com.laizexin.sdj.kotlindouban.listener.OnResultListener

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/10
 */
interface MovieDetailModel : IModel {

    fun getMovieDetail(id : Int,listener : OnResultListener)
}