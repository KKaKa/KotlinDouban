package com.laizexin.sdj.kotlindouban.contract.model

import com.laizexin.sdj.kotlindouban.contract.IModel
import com.laizexin.sdj.kotlindouban.listener.OnResultListener

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/17
 */
interface InTheatersModel : IModel {

    fun getInTheatersMovies(city : String,start : Int,counts : Int,onResultListener: OnResultListener)
}