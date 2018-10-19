package com.laizexin.sdj.kotlindouban

import android.app.Application
import android.content.Context
import com.laizexin.sdj.kotlindouban.utils.CustomLogCatStrategy
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/9/26
 */
class App : Application() {

    object AppContext{
        lateinit var context : Context
    }

    override fun onCreate() {
        super.onCreate()
        AppContext.context = applicationContext

        val formatStrategy : FormatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .logStrategy(CustomLogCatStrategy())
                .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy){
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    companion object {
        fun getContext() : Context{
            return AppContext.context
        }
    }


}