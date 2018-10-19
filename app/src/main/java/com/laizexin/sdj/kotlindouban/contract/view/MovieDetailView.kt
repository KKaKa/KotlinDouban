package com.laizexin.sdj.kotlindouban.contract.view

import com.laizexin.sdj.kotlindouban.contract.IView
import com.laizexin.sdj.kotlindouban.contract.presenter.MovieDetailPresenter
import com.laizexin.sdj.kotlindouban.bean.MovieSubject

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/10
 */
interface MovieDetailView : IView<MovieDetailPresenter> {

    fun getDataSuccess(movieSubject: MovieSubject)

    fun getDataFail(errorMsg : String)
}