package com.laizexin.sdj.kotlindouban.retrofit

import com.laizexin.sdj.kotlindouban.bean.Celebrity
import com.laizexin.sdj.kotlindouban.bean.Movie
import com.laizexin.sdj.kotlindouban.bean.MovieSubject
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/9/26
 */
interface ApiService{

    @GET("/v2/movie/top250")
    fun loadTopW250Movies(@Query("start") start: Int,
                          @Query("count") count: Int): Observable<Movie>

    @GET("v2/movie/search")
    fun searchMovies(@Query("q") q: String): Observable<Movie>

    @GET("/v2/movie/celebrity/{id}")
    fun loadCelebrity(@Path("id") id: Int): Observable<Celebrity>

    @GET("/v2/movie/subject/{id}")
    fun loadMovieDetail(@Path("id") id: Int) : Observable<MovieSubject>

    @GET("/v2/movie/in_theaters")
    fun loadInTheatersMovie(@Query("city") city: String,
                           @Query("start") start: Int,
                           @Query("count") count: Int) : Observable<Movie>
}