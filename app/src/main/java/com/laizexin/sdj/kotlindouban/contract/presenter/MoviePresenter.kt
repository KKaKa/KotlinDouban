package com.laizexin.sdj.kotlindouban.contract.presenter

import com.laizexin.sdj.kotlindouban.contract.IPresenter

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/9/29
 */
interface MoviePresenter : IPresenter{

    fun getMovies(start : Int,conuts : Int)

    fun getMore(start: Int,conuts: Int)

}