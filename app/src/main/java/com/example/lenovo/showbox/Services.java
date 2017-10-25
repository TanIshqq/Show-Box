package com.example.lenovo.showbox;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

/**
 * Created by Lenovo on 22-10-2017.
 */

public interface Services {

    @GET("movie/popular?api_key=4db11af8b81b5f51233f56e9078e9c07&language=en-US")
    Call<Movies1> getMovies();

    @GET("movie/upcoming?api_key=4db11af8b81b5f51233f56e9078e9c07&language=en-US&page=1%20Code%20Generati")
    Call<Movies1> getUpcomingMovies();

}
