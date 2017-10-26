package com.example.lenovo.showbox.Networking;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Lenovo on 22-10-2017.
 */

public interface Services {

    @GET("movie/popular?api_key=4db11af8b81b5f51233f56e9078e9c07&language=en-US&page=2")
    Call<Movies1> getMovies();

    @GET("movie/upcoming?api_key=4db11af8b81b5f51233f56e9078e9c07&language=en-US&page=3")
    Call<Movies1> getUpcomingMovies();

    @GET("movie/top_rated?api_key=4db11af8b81b5f51233f56e9078e9c07&language=en-US&page=1")
    Call<Movies1> getTopRatedMovies();

    @GET("movie/now_playing?api_key=4db11af8b81b5f51233f56e9078e9c07&language=en-US&page=1")
    Call<Movies1> getPlayingMovies();

    @GET("tv/popular?api_key=4db11af8b81b5f51233f56e9078e9c07&language=en-US&page=1")
    Call<TvShows1> getPopularTvShows();

    @GET("tv/airing_today?api_key=4db11af8b81b5f51233f56e9078e9c07&language=en-US&page=4")
    Call<TvShows1> getTodaysShows();

    @GET("tv/top_rated?api_key=4db11af8b81b5f51233f56e9078e9c07&language=en-US&page=1")
    Call<TvShows1> getTopRatedTvShows();

    @GET("tv/on_the_air?api_key=4db11af8b81b5f51233f56e9078e9c07&language=en-US&page=2")
    Call<TvShows1> getOnAirTvShows();



}
