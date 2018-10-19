package com.laizexin.sdj.kotlindouban.utils

import android.util.Log
import com.orhanobut.logger.LogStrategy

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/9/29
 */
class CustomLogCatStrategy : LogStrategy {

    private var last: Int = 0

    override fun log(priority: Int, tag: String?, message: String) {
        Log.println(priority, randomKey() + tag!!, message)
    }

    private fun randomKey(): String {
        var random = (10 * Math.random()).toInt()
        if (random == last) {
            random = (random + 1) % 10
        }
        last = random
        return random.toString()
    }

}