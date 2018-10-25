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
import com.laizexin.sdj.kotlindouban.adapter.WorkAdapter
import com.laizexin.sdj.kotlindouban.bean.Celebrity
import com.laizexin.sdj.kotlindouban.contract.presenter.CelebrityPresenter
import com.laizexin.sdj.kotlindouban.contract.presenter.CelebrityPresenterImpl
import com.laizexin.sdj.kotlindouban.contract.view.CelebrityView
import com.laizexin.sdj.kotlindouban.utils.loadImgUrl
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle
import com.trello.rxlifecycle2.LifecycleProvider
import kotlinx.android.synthetic.main.activity_celebrity.*

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/16
 */
class CelebrityActivity : AppCompatActivity() , CelebrityView{

    private var lifecycleProvider : LifecycleProvider<Lifecycle.Event> = AndroidLifecycle.createLifecycleProvider(this)
    private var presenter : CelebrityPresenter? = null

    companion object {
        fun toCelebrityActivity(context: Context, id : Int){
            val intent = Intent(context,CelebrityActivity::class.java)
            intent.putExtra(ActivityConstant.Celebrity_ID,id)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_celebrity)
        ct_layout.setExpandedTitleMargin(32,10,0,10)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val celebrityId = intent.getIntExtra(ActivityConstant.Celebrity_ID,0)
        presenter = CelebrityPresenterImpl(this,lifecycleProvider)
        presenter!!.getCelebrity(celebrityId)
    }

    override fun getDataSuccess(celebrity: Celebrity) {
        ct_layout.title = celebrity.name
        ct_layout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        ct_layout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)
        celebrity.avatars!!.medium?.let { iv.loadImgUrl(this,it) }
        tv_name.text = celebrity.name
        tv_name_en.text = celebrity.name_en
        tv_born_place.text = celebrity.born_place
        val adapter = WorkAdapter(this,R.layout.item_work, celebrity.works)
        rv_worker.setMyLayoutManager(LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false))
        rv_worker.adapter = adapter
        rv_worker.openAnimation(true).setDefaultAnimation()

        fab_btu.setOnClickListener { WebViewActivity.toWebViewActivity(this,celebrity.mobile_url.toString()) }
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