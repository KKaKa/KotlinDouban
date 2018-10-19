package com.laizexin.sdj.kotlindouban.contract.presenter

import com.laizexin.sdj.kotlindouban.contract.IPresenter

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/17
 */
interface InTheatersPresenter : IPresenter {

    fun getInTheatersMovies(city : String,start : Int,counts : Int)

    fun getMore(city : String,start : Int,counts : Int)

}