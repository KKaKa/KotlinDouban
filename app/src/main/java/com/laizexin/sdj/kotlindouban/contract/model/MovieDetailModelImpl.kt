package com.laizexin.sdj.kotlindouban.contract.model

import android.arch.lifecycle.Lifecycle
import android.content.Context
import com.laizexin.sdj.kotlindouban.bean.MovieSubject
import com.laizexin.sdj.kotlindouban.listener.OnResultListener
import com.laizexin.sdj.kotlindouban.retrofit.BaseCallbackObserve
import com.laizexin.sdj.kotlindouban.retrofit.BaseRetrofit
import com.trello.rxlifecycle2.LifecycleProvider

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/10
 */
class MovieDetailModelImpl(private val context: Context,private val lifecycleProvider : LifecycleProvider<Lifecycle.Event>) : MovieDetailModel {

    override fun getMovieDetail(id: Int,listener : OnResultListener) {
        BaseRetrofit.getMovieDetail(id,lifecycleProvider,object : BaseCallbackObserve<MovieSubject>(context){
            override fun onNext(t: MovieSubject) {
                listener.getSuccess(t)
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                e.message?.let { listener.getFail(it) }
            }
        })
    }

}