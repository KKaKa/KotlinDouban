package com.laizexin.sdj.kotlindouban.contract.view

import com.laizexin.sdj.kotlindouban.contract.IView
import com.laizexin.sdj.kotlindouban.contract.presenter.MoviePresenter
import com.laizexin.sdj.kotlindouban.bean.Movie

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/9/29
 */
interface MovieView : IView<MoviePresenter> {

    fun getMoviesSuccess(movie: Movie)

    fun getMoviesFail(errorMsg : String)

}