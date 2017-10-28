package com.example.lenovo.showbox.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lenovo.showbox.Adapters.Layout_adapter;
import com.example.lenovo.showbox.Adapters.SearchAdapter;
import com.example.lenovo.showbox.Adapters.SearchAdapter1;
import com.example.lenovo.showbox.Adapters.TvPopularAdapter;
import com.example.lenovo.showbox.Networking.Movies1;
import com.example.lenovo.showbox.Networking.Services;
import com.example.lenovo.showbox.Networking.TvShows1;
import com.example.lenovo.showbox.Networking.apiClient;
import com.example.lenovo.showbox.R;
import com.wang.avi.AVLoadingIndicatorView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search_Activity extends AppCompatActivity {

    RecyclerView movieRecyclerView;
    RecyclerView tvRecyclerView;
    private AVLoadingIndicatorView avi;
    TextView box1;
    TextView box2;
    Movies1.Movies mSmovies[];
    TvShows1.TvShows nSshows[];
    SearchAdapter SearchAdapter;
    SearchAdapter1 SearchAdapter1;


    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        query = b.getString("Query");
        movieRecyclerView = (RecyclerView) findViewById(R.id.movierecyclerView);
        tvRecyclerView = (RecyclerView) findViewById(R.id.tvrecyclerView);
        box1 = (TextView) findViewById(R.id.moviesearchbox);
        box2 = (TextView) findViewById(R.id.tvsearchbox);
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        Services service = apiClient.getClient().create(Services.class);
        Call<Movies1> searchMoviesResponseCall = service.searchMovies(query);
        Call<TvShows1> searchTvResponseCall = service.searchTvShows(query);
        avi.setVisibility(View.VISIBLE);
        avi.smoothToShow();

        searchMoviesResponseCall.enqueue(new Callback<Movies1>() {
            @Override
            public void onResponse(Call<Movies1> call, Response<Movies1> response) {
                Movies1 movies1 = response.body();
                mSmovies = movies1.getresults();
                setmyMsearchadapter();

            }

            @Override
            public void onFailure(Call<Movies1> call, Throwable t) {

            }
        });

        searchTvResponseCall.enqueue(new Callback<TvShows1>() {
            @Override
            public void onResponse(Call<TvShows1> call, Response<TvShows1> response) {
                TvShows1 tvshows1 = response.body();
                nSshows = tvshows1.getResults();
                setmyTsearchadapter();

            }

            @Override
            public void onFailure(Call<TvShows1> call, Throwable t) {

            }
        });
    }



    private void setmyMsearchadapter() {
        SearchAdapter = new SearchAdapter(this, mSmovies);
        movieRecyclerView.setAdapter(SearchAdapter);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(Search_Activity.this, LinearLayoutManager.HORIZONTAL,false));
        box1.setText("Movies");
        SearchAdapter.notifyDataSetChanged();
    }

    private void setmyTsearchadapter() {
        SearchAdapter1 = new SearchAdapter1(this, nSshows);
        tvRecyclerView.setAdapter(SearchAdapter1);
        tvRecyclerView.setLayoutManager(new LinearLayoutManager(Search_Activity.this, LinearLayoutManager.HORIZONTAL,false));
        avi.smoothToHide();
        avi.setVisibility(View.GONE);
        box2.setText("TV Shows");
        SearchAdapter1.notifyDataSetChanged();
    }


}

