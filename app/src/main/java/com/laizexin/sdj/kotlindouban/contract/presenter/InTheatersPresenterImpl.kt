package com.laizexin.sdj.kotlindouban.contract.presenter

import android.arch.lifecycle.Lifecycle
import com.laizexin.sdj.kotlindouban.contract.model.InTheatersModel
import com.laizexin.sdj.kotlindouban.contract.model.InTheatersModelImpl
import com.laizexin.sdj.kotlindouban.contract.view.InTheatersView
import com.laizexin.sdj.kotlindouban.bean.Movie
import com.laizexin.sdj.kotlindouban.listener.OnResultListener
import com.trello.rxlifecycle2.LifecycleProvider

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/17
 */
class InTheatersPresenterImpl(val view : InTheatersView,private val lifecycleProvider : LifecycleProvider<Lifecycle.Event>) : InTheatersPresenter {

    private val model : InTheatersModel

    init {
        model = InTheatersModelImpl(view.getCurrentContext(),lifecycleProvider)
    }

    override fun getInTheatersMovies(city: String, start: Int, counts: Int) {
        model.getInTheatersMovies(city,start,counts,object : OnResultListener{

            override fun <T> getSuccess(data: T) {
                view.setRefresh(true)
                view.getIntheatersMoviesSuccess(data as Movie)
            }

            override fun getFail(errorMsg: String) {
                view.setRefresh(true)
                view.getIntheatersMoviesFail(errorMsg)
            }
        })
    }

    override fun getMore(city: String, start: Int, counts: Int) {
        model.getInTheatersMovies(city,start,counts,object : OnResultListener{

            override fun <T> getSuccess(data: T) {
                view.setLoadMore(true)
                view.getIntheatersMoviesSuccess(data as Movie)
            }

            override fun getFail(errorMsg: String) {
                view.setLoadMore(true)
                view.getIntheatersMoviesFail(errorMsg)
            }
        })
    }
}