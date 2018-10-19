package com.laizexin.sdj.kotlindouban.contract.model

import android.arch.lifecycle.Lifecycle
import android.content.Context
import com.laizexin.sdj.kotlindouban.bean.Movie
import com.laizexin.sdj.kotlindouban.listener.OnResultListener
import com.laizexin.sdj.kotlindouban.retrofit.BaseCallbackObserve
import com.laizexin.sdj.kotlindouban.retrofit.BaseRetrofit
import com.trello.rxlifecycle2.LifecycleProvider

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/17
 */
class InTheatersModelImpl(private val context: Context, private val lifecycleProvider : LifecycleProvider<Lifecycle.Event>) : InTheatersModel {
    override fun getInTheatersMovies(city: String, start: Int, counts: Int, onResultListener: OnResultListener) {
        BaseRetrofit.getInTheatersMovie(city,start,counts,lifecycleProvider,object : BaseCallbackObserve<Movie>(context){
            override fun onNext(t: Movie) {
                onResultListener.getSuccess(t)
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                e.message?.let { onResultListener.getFail(it) }
            }
        })
    }
}