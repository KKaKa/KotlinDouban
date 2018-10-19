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
 * @Time: 2018/10/12
 */
class MovieSearchModelImpl(val context: Context,val lifecycleProvider : LifecycleProvider<Lifecycle.Event>) : MovieSearchModel {

    override fun searchMovie(queryString: String, onResultListener: OnResultListener) {
        BaseRetrofit.searchMovie(queryString,lifecycleProvider,object : BaseCallbackObserve<Movie>(context){
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