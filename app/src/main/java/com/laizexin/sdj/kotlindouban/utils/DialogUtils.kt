package com.laizexin.sdj.kotlindouban.utils

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/17
 */
object DialogUtils {

    fun showChooseCityDialog(context: Context,title:String,inputHint:String,listener:OnChangeListener){
        MaterialDialog.Builder(context)
                .title(title)
                .input(inputHint,"") { dialog, input ->
                    listener.onChange(input)
                }
                .positiveText("确定")
                .show()
    }

    interface OnChangeListener{
        fun onChange(city : CharSequence)
    }

}