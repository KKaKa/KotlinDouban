package com.laizexin.sdj.kotlindouban.contract.presenter

import android.arch.lifecycle.Lifecycle
import com.laizexin.sdj.kotlindouban.contract.model.CelebrityModel
import com.laizexin.sdj.kotlindouban.contract.model.CelebrityModelImpl
import com.laizexin.sdj.kotlindouban.contract.view.CelebrityView
import com.laizexin.sdj.kotlindouban.bean.Celebrity
import com.laizexin.sdj.kotlindouban.listener.OnResultListener
import com.trello.rxlifecycle2.LifecycleProvider

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/16
 */
class CelebrityPresenterImpl(val view : CelebrityView,private val lifecycleProvider : LifecycleProvider<Lifecycle.Event>) : CelebrityPresenter {
    val model : CelebrityModel

    init {
        this.model = CelebrityModelImpl(view.getCurrentContext(),lifecycleProvider)
    }

    override fun getCelebrity(id: Int) {
        model.getCelebrity(id,object : OnResultListener{
            override fun <T> getSuccess(data: T) {
                view.getDataSuccess(data as Celebrity)
            }

            override fun getFail(errorMsg: String) {
                view.getDataFail(errorMsg)
            }
        })
    }
}