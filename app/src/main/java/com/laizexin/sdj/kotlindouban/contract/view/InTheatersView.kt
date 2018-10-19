package com.laizexin.sdj.kotlindouban.contract.view

import com.laizexin.sdj.kotlindouban.contract.IView
import com.laizexin.sdj.kotlindouban.contract.presenter.InTheatersPresenter
import com.laizexin.sdj.kotlindouban.bean.Movie

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/17
 */
interface InTheatersView : IView<InTheatersPresenter> {

    fun getIntheatersMoviesSuccess(movies : Movie)

    fun getIntheatersMoviesFail(errorMsg : String)
}