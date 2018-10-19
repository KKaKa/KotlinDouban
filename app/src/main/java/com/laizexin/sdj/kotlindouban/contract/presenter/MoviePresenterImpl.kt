package com.laizexin.sdj.kotlindouban.contract.presenter

import android.arch.lifecycle.Lifecycle
import com.laizexin.sdj.kotlindouban.bean.Movie
import com.laizexin.sdj.kotlindouban.contract.model.MovieModel
import com.laizexin.sdj.kotlindouban.contract.model.MovieModelImpl
import com.laizexin.sdj.kotlindouban.contract.view.MovieView
import com.laizexin.sdj.kotlindouban.listener.OnResultListener
import com.trello.rxlifecycle2.LifecycleProvider

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/9/29
 */
class MoviePresenterImpl(val view: MovieView,val lifecycleProvider : LifecycleProvider<Lifecycle.Event>) : MoviePresenter {

    val model : MovieModel

    init {
        this.model = MovieModelImpl(view.getCurrentContext(),lifecycleProvider)
    }

    override fun getMovies(start: Int, conuts: Int) {
        model.getMovies(start,conuts,object : OnResultListener{

            override fun <T> getSuccess(data: T) {
                view.setRefresh(false)
                view.getMoviesSuccess(data as Movie)
            }

            override fun getFail(errorMsg: String) {
                view.setRefresh(false)
                view.getMoviesFail(errorMsg)
            }
        })
    }

    override fun getMore(start: Int, conuts: Int) {
        model.getMovies(start,conuts,object : OnResultListener{

            override fun <T> getSuccess(data: T) {
                view.setLoadMore(false)
                view.getMoviesSuccess(data as Movie)
            }

            override fun getFail(errorMsg: String) {
                view.setLoadMore(false)
                view.getMoviesFail(errorMsg)
            }
        })
    }
}