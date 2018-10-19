package com.laizexin.sdj.kotlindouban.contract.model

import com.laizexin.sdj.kotlindouban.contract.IModel
import com.laizexin.sdj.kotlindouban.listener.OnResultListener

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/12
 */
interface MovieSearchModel : IModel {

    fun searchMovie(queryString : String,onResultListener : OnResultListener)
}