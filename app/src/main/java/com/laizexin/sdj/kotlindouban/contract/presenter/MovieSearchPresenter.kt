package com.laizexin.sdj.kotlindouban.contract.presenter

import com.laizexin.sdj.kotlindouban.contract.IPresenter

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/12
 */
interface MovieSearchPresenter : IPresenter {

    fun searchMovie(queryString : String)

}