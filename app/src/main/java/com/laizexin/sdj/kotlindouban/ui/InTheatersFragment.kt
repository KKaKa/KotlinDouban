package com.laizexin.sdj.kotlindouban.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import com.laizexin.sdj.kotlindouban.R
import com.laizexin.sdj.kotlindouban.adapter.MoviesAdapter
import com.laizexin.sdj.kotlindouban.bean.Movie
import com.laizexin.sdj.kotlindouban.contract.presenter.InTheatersPresenter
import com.laizexin.sdj.kotlindouban.contract.presenter.InTheatersPresenterImpl
import com.laizexin.sdj.kotlindouban.contract.view.InTheatersView
import com.laizexin.sdj.kotlindouban.utils.DialogUtils
import com.scwang.smartrefresh.header.BezierCircleHeader
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import com.wooplr.spotlight.SpotlightView
import kotlinx.android.synthetic.main.fragment_setting.*



/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/9/25
 */
class InTheatersFragment : BaseFragment(), InTheatersView, MoviesAdapter.OnHolderClickListener {

    private var presenter : InTheatersPresenter? = null
    private var adapter : MoviesAdapter? = null

    private var start = 0
    private val counts = 10
    private var city = "深圳"
    private var isLoadMoreOrRefresh = true

    override fun onInflateLayout(): Int {
        return R.layout.fragment_setting
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationIcon(R.mipmap.icon_city)
        toolbar.setNavigationOnClickListener{
            DialogUtils.showChooseCityDialog(activity!!,
                    getString(R.string.change_city),getString(R.string.input_city_name),object : DialogUtils.OnChangeListener{
                override fun onChange(_city: CharSequence) {
                    if(TextUtils.isEmpty(_city))
                        return
                    city = _city.toString()
                    refresh_view.autoRefresh()
                }
            })
        }
        adapter = MoviesAdapter(getCurrentContext(),R.layout.item_movie,mutableListOf())
        adapter!!.setOnHolderClickListener(this)
        recycle_view.adapter = adapter
        presenter = InTheatersPresenterImpl(this,lifecycleProvider)
        initRefreshViewConfig()
    }

    private fun initRefreshViewConfig() {
        refresh_view.setOnRefreshListener {
            isLoadMoreOrRefresh = true
            start = 0
            presenter!!.getInTheatersMovies(city,start,counts)
        }

        refresh_view.setOnLoadMoreListener {
            isLoadMoreOrRefresh = false
            start+=counts
            presenter!!.getMore(city,start,counts)
        }

        refresh_view.setEnableRefresh(true)//启用下拉刷新
        refresh_view.setEnableLoadMore(true)//启用加载更多
        refresh_view.setEnableOverScrollBounce(true)//启用越界回弹
        refresh_view.setEnableScrollContentWhenLoaded(true)//加载完成时滚动列表显示新的内容
        refresh_view.setEnableLoadMoreWhenContentNotFull(true)//在列表不满一页时候开启上拉加载功能
        refresh_view.setEnableOverScrollDrag(true)//启用越界拖动
        val header = BezierCircleHeader(activity)
        refresh_view.setRefreshHeader(header)
        val footer = BallPulseFooter(activity!!)
        footer.setAnimatingColor(ContextCompat.getColor(activity!!,R.color.colorPrimaryDark))
        refresh_view.setRefreshFooter(footer)
        refresh_view.autoRefresh()
    }

    private fun showSpotlight(){
        val view = toolbar.getChildAt(0)

        SpotlightView.Builder(activity)
                .introAnimationDuration(400)
                .enableRevealAnimation(true)
                .performClick(true)
                .fadeinTextDuration(400)
                .headingTvColor(ContextCompat.getColor(activity,R.color.whitesmoke))
                .headingTvSize(32)
                .headingTvText(getString(R.string.change_city))
                .subHeadingTvColor(ContextCompat.getColor(activity,R.color.whitesmoke))
                .subHeadingTvSize(16)
                .subHeadingTvText(getString(R.string.change_city_tip))
                .maskColor(ContextCompat.getColor(activity,R.color.spotlight))
                .target(view)
                .lineAnimDuration(400)
                .lineAndArcColor(ContextCompat.getColor(activity,R.color.whitesmoke))
                .dismissOnTouch(true)
                .dismissOnBackPress(true)
                .enableDismissAfterShown(true)
                .usageId("01")
                .setListener { }
                .show();
    }

    override fun onHolderClick(id: Int) {
        MovieDetailActivity.toMovieDetailActivity(activity!!,id)
    }

    override fun getIntheatersMoviesSuccess(movies: Movie) {
        toolbar.title = movies.title
        if(isLoadMoreOrRefresh){
            adapter!!.clearData()
            recycle_view.setMyLayoutManager(LinearLayoutManager(getCurrentContext()))
                    .openAnimation(true)
                    .setDefaultAnimation()
                    .setEmptyView(iv_empty)
        }
        adapter!!.addDataAll(movies.subjects)
        recycle_view.adapter.notifyDataSetChanged()
        showSpotlight()
    }

    override fun getIntheatersMoviesFail(errorMsg: String) {

    }

    override fun setRefresh(isRefresh: Boolean) {
        refresh_view?.finishRefresh()
    }

    override fun setLoadMore(loadMore: Boolean) {
        refresh_view?.finishLoadMore()
    }

    override fun getCurrentContext(): Context {
        return activity!!
    }
}