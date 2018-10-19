package com.laizexin.sdj.kotlindouban.contract.presenter

import android.arch.lifecycle.Lifecycle
import com.laizexin.sdj.kotlindouban.contract.model.MovieSearchModel
import com.laizexin.sdj.kotlindouban.contract.model.MovieSearchModelImpl
import com.laizexin.sdj.kotlindouban.contract.view.MovieSearchView
import com.laizexin.sdj.kotlindouban.bean.Movie
import com.laizexin.sdj.kotlindouban.listener.OnResultListener
import com.trello.rxlifecycle2.LifecycleProvider

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/12
 */
class MovieSearchPresenterImpl(val view : MovieSearchView, val lifecycleProvider : LifecycleProvider<Lifecycle.Event>) : MovieSearchPresenter{

    val model : MovieSearchModel

    init {
        this.model = MovieSearchModelImpl(view.getCurrentContext(),lifecycleProvider)
    }

    override fun searchMovie(queryString: String) {
        model.searchMovie(queryString,object : OnResultListener {

            override fun <T> getSuccess(data: T) {
                view.searchMovieSuccess(data as Movie)
            }

            override fun getFail(errorMsg: String) {
                view.searchMovieFail(errorMsg)
            }

        })
    }
}