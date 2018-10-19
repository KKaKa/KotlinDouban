package com.laizexin.sdj.kotlindouban.ui

import android.arch.lifecycle.Lifecycle
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.text.Html
import android.view.View
import com.laizexin.sdj.kotlindouban.contract.presenter.MovieSearchPresenter
import com.laizexin.sdj.kotlindouban.contract.presenter.MovieSearchPresenterImpl
import com.laizexin.sdj.kotlindouban.contract.view.MovieSearchView
import com.laizexin.sdj.kotlindouban.R
import com.laizexin.sdj.kotlindouban.adapter.MoviesAdapter
import com.laizexin.sdj.kotlindouban.bean.Movie
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle
import com.trello.rxlifecycle2.LifecycleProvider
import kotlinx.android.synthetic.main.activity_search_movie.*
import kotlinx.android.synthetic.main.layout_search_error.*

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/12
 */
class MovieSearchActivity : AppCompatActivity() , MovieSearchView,MoviesAdapter.OnHolderClickListener{
    private var presenter : MovieSearchPresenter? = null
    private var adapter : MoviesAdapter? = null
    private var queryText: String? = null
    private var lifecycleProvider : LifecycleProvider<Lifecycle.Event> = AndroidLifecycle.createLifecycleProvider(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)
        presenter = MovieSearchPresenterImpl(this,lifecycleProvider)
        initSearchView()
        adapter = MoviesAdapter(this,R.layout.item_movie, mutableListOf())
        search_recycler_view.setMyLayoutManager(LinearLayoutManager(this))
                .setDefaultAnimation()
                .openAnimation(true)
        search_recycler_view.adapter = adapter
        adapter!!.setOnHolderClickListener(this)
    }

    private fun initSearchView() {
        //设置搜索框直接展开显示。左侧有放大镜(在搜索框中) 右侧有叉叉 可以关闭搜索框
        search_view.setIconified(false)
        //搜索框展开时后面叉叉按钮的点击事件
        search_view.setOnCloseListener {
            adapter?.clearData()
            true
        }
        //监听内容变化
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter?.searchMovie(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter?.searchMovie(newText!!)
                queryText = newText
                return true
            }

        })

        tv_error_info.setOnClickListener{
            presenter?.searchMovie(queryText!!)
        }
    }

    override fun searchMovieSuccess(movie: Movie) {
        adapter!!.clearData()
        adapter!!.addDataAll(movie.subjects)
        adapter!!.notifyDataSetChanged()
        error_view.visibility = View.GONE
    }

    override fun searchMovieFail(errorMsg: String) {
        adapter!!.clearData()
        adapter!!.notifyDataSetChanged()
        error_view.visibility = View.VISIBLE
        when(android.os.Build.VERSION.SDK_INT){
            android.os.Build.VERSION_CODES.N -> tv_error_info.text = Html.fromHtml(getString(R.string.search_error), Html.FROM_HTML_MODE_LEGACY)
            else -> tv_error_info.text = Html.fromHtml(getString(R.string.search_error)).toString()+"\n$errorMsg"
        }
    }

    override fun onHolderClick(id: Int) {
        MovieDetailActivity.toMovieDetailActivity(this,id)
    }

    override fun setRefresh(isRefresh: Boolean) {
    }

    override fun setLoadMore(loadMore: Boolean) {
    }

    override fun getCurrentContext(): Context {
        return this
    }

    companion object {
        fun toMovieSearchActivity(context: Context){
            val intent = Intent(context,MovieSearchActivity::class.java)
            context.startActivity(intent)
        }
    }
}