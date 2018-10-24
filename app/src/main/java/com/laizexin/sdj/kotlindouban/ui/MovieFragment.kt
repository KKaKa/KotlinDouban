package com.laizexin.sdj.kotlindouban.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.laizexin.sdj.kotlindouban.R
import com.laizexin.sdj.kotlindouban.adapter.MoviesAdapter
import com.laizexin.sdj.kotlindouban.bean.Movie
import com.laizexin.sdj.kotlindouban.contract.presenter.MoviePresenter
import com.laizexin.sdj.kotlindouban.contract.presenter.MoviePresenterImpl
import com.laizexin.sdj.kotlindouban.contract.view.MovieView
import com.laizexin.sdj.kotlindouban.utils.Utils
import com.scwang.smartrefresh.header.BezierCircleHeader
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/9/25
 */
class MovieFragment : BaseFragment(),MovieView, MoviesAdapter.OnHolderClickListener {

    private var presenter : MoviePresenter? = null
    private var adapter : MoviesAdapter? = null

    //开始条目
    private var start = 0
    //每页条目数
    private val counts = 10
    //是刷新或者加载更多 true为刷新 false为加载更多
    private var isLoadMoreOrRefresh = true

    override fun onInflateLayout(): Int {
        return R.layout.fragment_main
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.title = getString(R.string.tab_home)
        toolbar.inflateMenu(R.menu.main_menu)
        toolbar.setOnMenuItemClickListener {
            val intent = Intent(activity,AboutActivity::class.java)
            startActivity(intent)
             true
        }
        adapter = MoviesAdapter(getCurrentContext(),R.layout.item_movie,mutableListOf())
        adapter!!.setOnHolderClickListener(this)
        recycle_view.adapter = adapter
        presenter = MoviePresenterImpl(this,lifecycleProvider)
        initRefreshViewConfig()
        relative_search.setOnClickListener {
            MovieSearchActivity.toMovieSearchActivity(activity!!)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main_menu,menu)
    }

    private fun initRefreshViewConfig() {
        refresh_view.setOnRefreshListener {
            isLoadMoreOrRefresh = true
            start = 0
            presenter!!.getMovies(start,counts)
        }

        refresh_view.setOnLoadMoreListener {
            isLoadMoreOrRefresh = false
            start+=counts
            presenter!!.getMore(start,counts)
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

    override fun getMoviesSuccess(movie: Movie) {
        Utils.LogInfo(TAG!!,movie.toString())
        if(isLoadMoreOrRefresh){
            adapter!!.clearData()
            recycle_view.setMyLayoutManager(LinearLayoutManager(getCurrentContext()))
                    .openAnimation(true)
                    .setDefaultAnimation()
                    .setEmptyView(iv_empty)
        }
        adapter!!.addDataAll(movie.subjects)
        recycle_view.adapter!!.notifyDataSetChanged()
    }

    override fun getMoviesFail(errorMsg: String) {

    }

    override fun onHolderClick(id : Int) {
        MovieDetailActivity.toMovieDetailActivity(activity!!,id)
    }

    override fun setRefresh(isRefresh: Boolean) {
        refresh_view?.finishRefresh()
    }

    override fun setLoadMore(loadMore: Boolean) {
        refresh_view?.finishLoadMore()
    }

    override fun getCurrentContext(): Context {
        return this.activity!!
    }

}