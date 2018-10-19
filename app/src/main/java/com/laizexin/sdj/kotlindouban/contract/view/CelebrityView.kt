package com.laizexin.sdj.kotlindouban.contract.view

import com.laizexin.sdj.kotlindouban.contract.IView
import com.laizexin.sdj.kotlindouban.contract.presenter.CelebrityPresenter
import com.laizexin.sdj.kotlindouban.bean.Celebrity

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/16
 */
interface CelebrityView : IView<CelebrityPresenter> {

    fun getDataSuccess(celebrity: Celebrity)

    fun getDataFail(errorMsg : String)
}