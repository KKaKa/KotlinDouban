package com.laizexin.sdj.kotlindouban.ui

import android.arch.lifecycle.Lifecycle
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.laizexin.sdj.kotlindouban.ActivityConstant
import com.laizexin.sdj.kotlindouban.R
import com.laizexin.sdj.kotlindouban.adapter.ActorAdapter
import com.laizexin.sdj.kotlindouban.bean.Actor
import com.laizexin.sdj.kotlindouban.bean.MovieSubject
import com.laizexin.sdj.kotlindouban.contract.presenter.MovieDetailPresenter
import com.laizexin.sdj.kotlindouban.contract.presenter.MovieDetailPresenterImpl
import com.laizexin.sdj.kotlindouban.contract.view.MovieDetailView
import com.laizexin.sdj.kotlindouban.utils.Utils
import com.laizexin.sdj.kotlindouban.utils.loadImgUrl
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle
import com.trello.rxlifecycle2.LifecycleProvider
import kotlinx.android.synthetic.main.acitivity_movie_detail.*

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/10
 */
class MovieDetailActivity : AppCompatActivity(),MovieDetailView {

    private var presenter : MovieDetailPresenter? = null
    private var lifecycleProvider : LifecycleProvider<Lifecycle.Event> = AndroidLifecycle.createLifecycleProvider(this)

    companion object {
        fun toMovieDetailActivity(context: Context,id : Int){
            val intent = Intent(context,MovieDetailActivity::class.java)
            intent.putExtra(ActivityConstant.MOVIE_ID,id)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_movie_detail)
        ct_layout.setExpandedTitleMargin(32,10,0,10)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val movieId = intent.getIntExtra(ActivityConstant.MOVIE_ID,0)
        presenter = MovieDetailPresenterImpl(this,lifecycleProvider)
        presenter!!.getMovieDetail(movieId)
    }

    override fun getDataSuccess(movieSubject: MovieSubject) {
        Utils.LogInfo(TAG = "", info = movieSubject.toString())
        movieSubject.images!!.large?.let { iv.loadImgUrl(this, it) }
        //标题
        ct_layout.title = movieSubject.title
        ct_layout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        ct_layout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)
        //评分
        rating_bar.rating = movieSubject.rating?.stars!!.toFloat().div(10)
        tv_average.text = movieSubject.rating?.average?.toString()
        //想看人数
        tv_wish.text = getString(R.string.wish_watch).format(movieSubject.wish_count)
        tv_watched.text = getString(R.string.watched).format(movieSubject.collect_count)
        //电影信息
        tv_movie_info.text = getString(R.string.movie_info).format(movieSubject.year,movieSubject.original_title,Utils.parseString(movieSubject.genres.toString()),Utils.parseString(movieSubject.countries.toString()))
        //简介
        tv_summary.text = getString(R.string.movie_summary).format(movieSubject.summary)
        //演员导演表
        val adapter = ActorAdapter(this,R.layout.item_actor,movieSubject.casts!!.mapTo(mutableListOf()) {
            Actor(it.avatars?.medium,it.name,it.id)
        })
        val directors = movieSubject.directors!!.mapTo(mutableListOf()){
            Actor(it.avatars?.medium,it.name,it.id)
        }
        adapter.addDataAll(directors)
        rv_actor.setMyLayoutManager(LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false))
        rv_actor.adapter = adapter
        rv_actor.openAnimation(true).setDefaultAnimation()

        fab_btu.setOnClickListener { WebViewActivity.toWebViewActivity(this,movieSubject.mobile_url.toString()) }
    }

    override fun getDataFail(errorMsg: String) {

    }

    override fun setRefresh(isRefresh: Boolean) {

    }

    override fun setLoadMore(loadMore: Boolean) {

    }

    override fun getCurrentContext(): Context {
        return this
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> super.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
