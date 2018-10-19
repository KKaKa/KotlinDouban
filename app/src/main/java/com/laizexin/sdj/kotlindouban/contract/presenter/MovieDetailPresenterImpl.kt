package com.laizexin.sdj.kotlindouban.contract.presenter

import android.arch.lifecycle.Lifecycle
import com.laizexin.sdj.kotlindouban.contract.model.MovieDetailModel
import com.laizexin.sdj.kotlindouban.contract.model.MovieDetailModelImpl
import com.laizexin.sdj.kotlindouban.contract.view.MovieDetailView
import com.laizexin.sdj.kotlindouban.bean.MovieSubject
import com.laizexin.sdj.kotlindouban.listener.OnResultListener
import com.trello.rxlifecycle2.LifecycleProvider

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/10
 */
class MovieDetailPresenterImpl(val view : MovieDetailView,private val lifecycleProvider : LifecycleProvider<Lifecycle.Event>) : MovieDetailPresenter {

    val model : MovieDetailModel

    init {
        this.model = MovieDetailModelImpl(view.getCurrentContext(),lifecycleProvider)
    }

    override fun getMovieDetail(id: Int) {
        model.getMovieDetail(id,object : OnResultListener{
            override fun <T> getSuccess(data: T) {
                view.getDataSuccess(data as MovieSubject)
            }

            override fun getFail(errorMsg: String) {
                view.getDataFail(errorMsg)
            }
        })
    }
}