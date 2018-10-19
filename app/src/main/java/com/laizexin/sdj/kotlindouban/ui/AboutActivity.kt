package com.laizexin.sdj.kotlindouban.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.laizexin.sdj.kotlindouban.R
import com.mikepenz.aboutlibraries.LibsBuilder
import kotlinx.android.synthetic.main.activity_about.*

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/10/10
 */
class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        toolbar.title = getString(R.string.about)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var libFragment = LibsBuilder().supportFragment()
        supportFragmentManager.beginTransaction()
                .add(R.id.about_frame_container, libFragment)
                .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> super.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}