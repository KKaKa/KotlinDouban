package com.laizexin.sdj.kotlindouban.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.just.agentweb.AgentWeb
import com.laizexin.sdj.kotlindouban.R
import kotlinx.android.synthetic.main.activity_web_view.*

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/15
 */
class WebViewActivity : AppCompatActivity() {
    private var agentWebView : AgentWeb? = null

    companion object {
        private val WEB_URL = "web_url"
        fun toWebViewActivity(context: Context,url:String){
            val intent = Intent(context,WebViewActivity::class.java)
            intent.putExtra(WEB_URL,url)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val url = intent?.getStringExtra(WEB_URL)

        agentWebView = AgentWeb.with(this)
                .setAgentWebParent(refresh_layout, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator(-1,3)
                .setMainFrameErrorView(R.layout.agentweb_error_page,-1)
                .setWebChromeClient(mWebChromeClient)
                .createAgentWeb()
                .ready()
                .go(url)

        agentWebView?.webCreator?.webView?.setDownloadListener { _url, _, _, _, _ ->
            if (url!!.contains(".apk")) {
                val uri = Uri.parse(_url)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
        }

        refresh_layout.setOnRefreshListener {
            agentWebView?.urlLoader?.reload()
            refresh_layout.postDelayed({refresh_layout.isRefreshing = false},3000)
        }

        refresh_layout.setOnChildScrollUpCallback(SwipeRefreshLayout.OnChildScrollUpCallback { parent, child ->
            if (agentWebView != null) {
                return@OnChildScrollUpCallback agentWebView!!.webCreator.webView.getScrollY() > 0
            }
            false
        })
    }

    val mWebChromeClient = object :WebChromeClient(){
        override fun onReceivedTitle(view: WebView?, title: String?) {
            toolbar.title = title
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                if(agentWebView?.back()!!){
                    agentWebView?.back()
                }else{
                    super.onBackPressed()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if(agentWebView?.back()!!){
            agentWebView?.back()
        }else{
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        agentWebView?.webLifeCycle?.onResume()
    }

    override fun onPause() {
        super.onPause()
        agentWebView?.webLifeCycle?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        agentWebView?.webLifeCycle?.onPause()
    }

}