package com.laizexin.sdj.kotlindouban.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.laizexin.sdj.kotlindouban.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentF : Fragment? = null
    private var fragments : MutableList<Fragment>? = null
    private var movieFragment : MovieFragment? = null
    private var inTheatersFragment : InTheatersFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragments()
        initBottomNavigationView()
    }

    private fun initFragments(){
        fragments = mutableListOf();
        movieFragment = MovieFragment()
        inTheatersFragment = InTheatersFragment()
        fragments!!.add(movieFragment!!)
        fragments!!.add(inTheatersFragment!!)
        showFragment(0)
    }

    private fun initBottomNavigationView(){
        bottom_view.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home -> {
                    showFragment(0)
                    true
                }
                R.id.navigation_setting -> {
                    showFragment(1)
                    true
                }
                else -> {false}
            }
        }
    }

    private fun showFragment(index : Int){
        val fragmentTrans = supportFragmentManager.beginTransaction()
        if(currentF != null)
            fragmentTrans.hide(currentF!!)
        currentF = fragments!![index]
        if(!currentF!!.isAdded){
            fragmentTrans.add(R.id.main_container,currentF!!)
        }
        fragmentTrans.show(currentF!!)
        fragmentTrans.commitAllowingStateLoss()
    }
}
