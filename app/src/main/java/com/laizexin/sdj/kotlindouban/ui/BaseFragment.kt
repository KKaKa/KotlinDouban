package com.laizexin.sdj.kotlindouban.ui

import android.arch.lifecycle.Lifecycle
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle
import com.trello.rxlifecycle2.LifecycleProvider

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/9/25
 */
abstract class BaseFragment : Fragment() {

    protected var attachContext: Context? = null
    protected var TAG: String? = null
    protected var rootView: View? = null
    protected var lifecycleProvider : LifecycleProvider<Lifecycle.Event> = AndroidLifecycle.createLifecycleProvider(this)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        attachContext = context
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        TAG = this.javaClass.name

        rootView = inflater.inflate(onInflateLayout(), null)
        setHasOptionsMenu(hasOptionMenu())
        initView(rootView!!)
        initListener()
        initData()
        return rootView
    }

    protected abstract fun onInflateLayout(): Int

    /**
     * 设置该页面是否有菜单项
     *
     * @return
     */
    protected fun hasOptionMenu(): Boolean {
        return false
    }

    protected open fun initView(view: View) {

    }

    protected open fun initListener() {

    }

    protected open fun initData() {
        // TODO Auto-generated method stub

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
        attachContext = null
    }
}
