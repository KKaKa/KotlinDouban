package com.laizexin.sdj.kotlindouban.contract

import android.content.Context

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/9/29
 */
interface IView<T : IPresenter> {

    fun setRefresh(isRefresh : Boolean)

    fun setLoadMore(loadMore : Boolean)

    fun getCurrentContext() : Context
}