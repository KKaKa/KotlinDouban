package com.laizexin.sdj.kotlindouban.retrofit

import android.content.Context
import com.laizexin.sdj.kotlindouban.utils.Utils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/9/26
 */
abstract class BaseCallbackObserve<T>(internal var context: Context) : Observer<T> {

    override fun onSubscribe(d: Disposable) {

    }

    abstract override fun onNext(t: T)

    override fun onError(e: Throwable) {
        e.printStackTrace()
        Utils.showToast(context, e.message!!)
    }

    override fun onComplete() {

    }
}