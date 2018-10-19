package com.laizexin.sdj.kotlindouban.contract.model

import android.arch.lifecycle.Lifecycle
import android.content.Context
import com.laizexin.sdj.kotlindouban.bean.Celebrity
import com.laizexin.sdj.kotlindouban.listener.OnResultListener
import com.laizexin.sdj.kotlindouban.retrofit.BaseCallbackObserve
import com.laizexin.sdj.kotlindouban.retrofit.BaseRetrofit
import com.trello.rxlifecycle2.LifecycleProvider

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/16
 */
class CelebrityModelImpl(private val context: Context, private val lifecycleProvider : LifecycleProvider<Lifecycle.Event>) : CelebrityModel {

    override fun getCelebrity(id: Int, listener: OnResultListener) {
        BaseRetrofit.getCelebrity(id,lifecycleProvider,object : BaseCallbackObserve<Celebrity>(context){
            override fun onNext(t: Celebrity) {
                listener.getSuccess(t)
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                e.message?.let { listener.getFail(it) }
            }
        })
    }

}