package com.laizexin.sdj.kotlindouban.contract.model

import com.laizexin.sdj.kotlindouban.contract.IModel
import com.laizexin.sdj.kotlindouban.listener.OnResultListener

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/9/29
 */
interface MovieModel : IModel{

    fun getMovies(start : Int,conuts : Int,onResultListener: OnResultListener)

}