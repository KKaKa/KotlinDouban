package com.laizexin.sdj.kotlindouban.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.laizexin.sdj.kotlindouban.App
import com.laizexin.sdj.kotlindouban.R
import com.orhanobut.logger.Logger

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/9/26
 */
object Utils {

    /**
     * 检测是否联网
     */
    fun isOnline() : Boolean{
        val connectivity : ConnectivityManager = App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivity.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }

    fun showToast(context : Context,msg : String){
        val parent : View = (context as Activity).window.getDecorView().findViewById(android.R.id.content)
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val actionText : String? = null
        val actionListener : View.OnClickListener? = null

        val active = inputMethodManager.isActive
        if (active) {
            inputMethodManager.hideSoftInputFromWindow(parent.getWindowToken(), 0)
        }
        Snackbar.make(parent, msg, Snackbar.LENGTH_LONG)
                .setAction(actionText, actionListener)
                .setActionTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .show()
    }

    fun LogInfo(TAG:String,info:String){
        Logger.t(TAG).i(info)
    }

    fun LogJson(TAG:String,json:String){
        Logger.t(TAG).json(json)
    }

    fun LogD(TAG:String,msg:String){
        Logger.t(TAG).d(msg)
    }

    fun parseString(str: String): String {
        val result = str
                .replace("[", "")
                .replace("]", "")
                .replace(",", "/")
                .replace(" ", "")
        return result
    }
}