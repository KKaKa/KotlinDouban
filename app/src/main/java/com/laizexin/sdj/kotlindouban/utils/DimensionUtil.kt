package com.laizexin.sdj.kotlindouban.utils

import android.content.Context
import android.content.res.Resources

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/9/29
 */
class DimensionUtil {

    companion object {
        fun dpToPx(dp: Int): Int {
            return (dp * Resources.getSystem().displayMetrics.density).toInt()
        }

        fun dip2px(context: Context, dpValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }

        fun px2dip(context: Context, pxValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (pxValue / scale + 0.5f).toInt()
        }

        fun px2dip(context: Context, pxValue: Int): Int {
            val scale = context.resources.displayMetrics.density
            return (pxValue / scale + 0.5f).toInt()
        }

        fun px2sp(pxValue: Float, fontScale: Float): Int {
            return (pxValue / fontScale + 0.5f).toInt()
        }

        fun sp2px(spValue: Float, fontScale: Float): Int {
            return (spValue * fontScale + 0.5f).toInt()
        }

        fun px2sp(context: Context, pxValue: Float): Int {
            val fontScale = context.resources.displayMetrics.scaledDensity
            return (pxValue / fontScale + 0.5f).toInt()
        }

        fun px2sp(context: Context, pxValue: Int): Int {
            val fontScale = context.resources.displayMetrics.scaledDensity
            return (pxValue / fontScale + 0.5f).toInt()
        }

        fun sp2px(context: Context, spValue: Float): Int {
            val fontScale = context.resources.displayMetrics.scaledDensity
            return (spValue * fontScale + 0.5f).toInt()
        }
    }
}