package com.laizexin.sdj.kotlindouban.retrofit

import com.laizexin.sdj.kotlindouban.utils.Utils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/9/26
 */
object BaseInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if(Utils.isOnline()){
            val maxAge = 0
            response.newBuilder().removeHeader("Cache-Control")
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=$maxAge")
                    .build()
        }else{
            val maxAge = 60*60*24*7
            response.newBuilder().removeHeader("Cache-Control")
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxAge")
                    .build()
        }
        return response
    }

}