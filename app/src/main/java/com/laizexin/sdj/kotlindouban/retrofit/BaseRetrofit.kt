package com.laizexin.sdj.kotlindouban.retrofit

import android.arch.lifecycle.Lifecycle
import com.laizexin.sdj.kotlindouban.App
import com.laizexin.sdj.kotlindouban.bean.Celebrity
import com.laizexin.sdj.kotlindouban.bean.Movie
import com.laizexin.sdj.kotlindouban.bean.MovieSubject
import com.laizexin.sdj.kotlindouban.utils.Utils
import com.trello.rxlifecycle2.LifecycleProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/9/26
 */
    object BaseRetrofit {

    private const val cacheSize: Long = 10 * 1024 * 1024
    private const val CONNECT_TIME_OUT : Long = 10
    private const val WRITE_TIME_OUT : Long = 10
    private const val READ_TIME_OUT : Long = 10

    private fun getClient(): OkHttpClient{
        val builder = OkHttpClient.Builder()
        if(builder.interceptors() != null)
            builder.interceptors().clear()

        val logInterceptor = HttpLoggingInterceptor(HttpLogger())
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val cacheDirectory = File(App.getContext().cacheDir,"responses")
        val cache = Cache(cacheDirectory, cacheSize)

        builder.addInterceptor{chain ->
            val request = chain.request()

            if (!Utils.isOnline()) {
                chain.proceed(request!!
                        .newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build())
            } else {
                chain.proceed(
                        request!!.newBuilder().build())
            }
        }.addNetworkInterceptor(BaseInterceptor)
                .addNetworkInterceptor(logInterceptor)
                .cache(cache)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT,TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT,TimeUnit.SECONDS)
        return builder.build()
    }

    private fun retrofit(url : String) : Retrofit{
        return Retrofit.Builder()
                .baseUrl(url)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    fun getMovies(start : Int, conuts : Int,lifecycleProvider : LifecycleProvider<Lifecycle.Event>, observe : BaseCallbackObserve<Movie>){
        retrofit(APIs.DOU_BAN_URL).create(ApiService :: class.java)
                .loadTopW250Movies(start,conuts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(lifecycleProvider.bindUntilEvent(Lifecycle.Event.ON_DESTROY))
                .subscribe(observe)
    }

    fun getMovieDetail(id : Int,lifecycleProvider : LifecycleProvider<Lifecycle.Event>,observe: BaseCallbackObserve<MovieSubject>){
        retrofit(APIs.DOU_BAN_URL).create(ApiService :: class.java)
                .loadMovieDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(lifecycleProvider.bindUntilEvent(Lifecycle.Event.ON_DESTROY))
                .subscribe(observe)
    }

    fun searchMovie(queryString :String,lifecycleProvider : LifecycleProvider<Lifecycle.Event>,observe: BaseCallbackObserve<Movie>){
        retrofit(APIs.DOU_BAN_URL).create(ApiService :: class.java)
                .searchMovies(queryString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(lifecycleProvider.bindUntilEvent(Lifecycle.Event.ON_DESTROY))
                .subscribe(observe)
    }

    fun getCelebrity(id : Int, lifecycleProvider: LifecycleProvider<Lifecycle.Event>, observe: BaseCallbackObserve<Celebrity>){
        retrofit(APIs.DOU_BAN_URL).create(ApiService ::class.java)
                .loadCelebrity(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(lifecycleProvider.bindUntilEvent(Lifecycle.Event.ON_DESTROY))
                .subscribe(observe)
    }

    fun getInTheatersMovie(city : String,start: Int,conuts: Int,lifecycleProvider : LifecycleProvider<Lifecycle.Event>, observe : BaseCallbackObserve<Movie>){
        retrofit(APIs.DOU_BAN_URL).create(ApiService::class.java)
                .loadInTheatersMovie(city,start,conuts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(lifecycleProvider.bindUntilEvent(Lifecycle.Event.ON_DESTROY))
                .subscribe(observe)
    }

}