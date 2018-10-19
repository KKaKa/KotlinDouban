package com.laizexin.sdj.kotlindouban.retrofit

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/9/29
 */
class HttpLogger : HttpLoggingInterceptor.Logger {

    private val mMessage = StringBuilder()

    override fun log(message: String?) {
//        var changeMsg : String? = null
//        // 请求或者响应开始
//        if (message!!.startsWith("--> POST")) {
//            mMessage.setLength(0);
//        }
//        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
//        if ((message.startsWith("{") && message.endsWith("}"))
//                || (message.startsWith("[") && message.endsWith("]"))) {
//            changeMsg = JsonUtil.formatJson(JsonUtil.decodeUnicode(message));
//        }
//        mMessage.append(changeMsg + "\n");
//        // 响应结束，打印整条日志
//        if (message.startsWith("<-- END HTTP")) {
//            Utils.LogD("HttpLogger",mMessage.toString())
//        }
        Log.i("HttpLogger",message)
    }

}