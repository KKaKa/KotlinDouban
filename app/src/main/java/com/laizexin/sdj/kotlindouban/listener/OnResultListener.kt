package com.laizexin.sdj.kotlindouban.listener

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/9/29
 */
interface OnResultListener {
    fun <T> getSuccess(data : T)
    fun getFail(errorMsg : String)
}