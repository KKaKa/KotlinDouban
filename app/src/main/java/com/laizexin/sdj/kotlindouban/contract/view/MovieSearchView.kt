package com.laizexin.sdj.kotlindouban.contract.view

import com.laizexin.sdj.kotlindouban.contract.IView
import com.laizexin.sdj.kotlindouban.contract.presenter.MovieSearchPresenter
import com.laizexin.sdj.kotlindouban.bean.Movie

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/12
 */
interface MovieSearchView : IView<MovieSearchPresenter> {

    fun searchMovieSuccess(movie: Movie)

    fun searchMovieFail(errorMsg : String)
}