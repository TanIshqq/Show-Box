package com.example.lenovo.showbox.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.showbox.Adapters.DiscoverAdapter;
import com.example.lenovo.showbox.Adapters.DiscoverAdapter1;
import com.example.lenovo.showbox.Adapters.SearchAdapter;
import com.example.lenovo.showbox.Adapters.SearchAdapter1;
import com.example.lenovo.showbox.Networking.Movies1;
import com.example.lenovo.showbox.Networking.Services;
import com.example.lenovo.showbox.Networking.TvShows1;
import com.example.lenovo.showbox.Networking.apiClient;
import com.example.lenovo.showbox.R;
import com.wang.avi.AVLoadingIndicatorView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverResult extends AppCompatActivity {

    RecyclerView DiscoveryRecyclerView;
    private AVLoadingIndicatorView avi;
    TextView box1;
    String category,language,adult,sortby,ratings,year;
    Movies1.Movies mDmovies[];
    TvShows1.TvShows nDshows[];
    DiscoverAdapter DiscoverAdapter;
    DiscoverAdapter1 DiscoverAdapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_result);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        category = b.getString("Category");
        language = b.getString("Language");
        ratings = b.getString("Ratings");
        year = b.getString("Year");
        sortby = b.getString("SortBy");
        adult = b.getString("Adult");

        if(category=="Movies"){
            category="movie";
        }
        else if(category=="Tv Shows"){
            category="tv";
        }

        if(language=="English"){
            language="en-US";
        }
        else if(language=="Hindi"){
            language="hi-IN";
        }
        else if(language=="French"){
            language="fr-FR";
        }
        else if(language=="Japanese"){
            language="ja-JP";
        }

        if(year=="Beyond 2010"){
            year="2010";
        }
        else if(year=="Beyond 2000"){
            year="2000";
        }
        else if(year=="Beyond 1990"){
            year="1990";
        }
        else if(year=="Beyond 1980"){
            year="1980";
        }
        else if(year=="Beyond 1970"){
            year="1970";
        }

        if(adult=="Adult"){
            adult="true";
        }
        else if(adult=="U/A" || adult=="U"){
            adult="false";
        }

        if(sortby=="Popularity Ascending"){
            sortby="popularity.asc";
        }
        else if(sortby=="Popularity Descending"){
            sortby="popularity.desc";
        }
        else if(sortby=="Ratings Ascending"){
            sortby="vote_average.asc";
        }
        else if(sortby=="Ratings Descending"){
            sortby="vote_average.desc";
        }


        DiscoveryRecyclerView = (RecyclerView) findViewById(R.id.discoverecyclerView);
        box1 = (TextView) findViewById(R.id.discovertextbox);
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        Services service = apiClient.getClient().create(Services.class);
        if(category=="movie"){
            Log.d("ghus gaya",category);
            Call<Movies1> discoverMoviesResponseCall = service.discoverMovies(language,sortby,ratings,adult,year);
            avi.setVisibility(View.VISIBLE);
            avi.smoothToShow();
            discoverMoviesResponseCall.enqueue(new Callback<Movies1>() {
                @Override
                public void onResponse(Call<Movies1> call, Response<Movies1> response) {
                    Movies1 movies1 = response.body();
                    mDmovies = movies1.getresults();
                    setmyMdiscoveradapter();

                }

                @Override
                public void onFailure(Call<Movies1> call, Throwable t) {

                }
            });

        }
        else if(category=="tv"){
            Call<TvShows1> discoverTvResponseCall = service.discoverTvShows(language,sortby,ratings);
            avi.setVisibility(View.VISIBLE);
            avi.smoothToShow();
            discoverTvResponseCall.enqueue(new Callback<TvShows1>() {
                @Override
                public void onResponse(Call<TvShows1> call, Response<TvShows1> response) {
                    TvShows1 tvshows1 = response.body();
                    nDshows = tvshows1.getResults();
                    setmyTdiscoveradapter();

                }

                @Override
                public void onFailure(Call<TvShows1> call, Throwable t) {

                }
            });

        }



    }

    private void setmyMdiscoveradapter() {
        DiscoverAdapter = new DiscoverAdapter(this, mDmovies, new DiscoverAdapter.MovieClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        if(mDmovies.length==0){
            Toast.makeText(this,"No Movies / Tv Shows Found",Toast.LENGTH_SHORT).show();
            finish();

        }
        else{
            DiscoveryRecyclerView.setAdapter(DiscoverAdapter);
            DiscoveryRecyclerView.setLayoutManager(new GridLayoutManager(DiscoverResult.this,3));
            avi.smoothToHide();
            avi.setVisibility(View.GONE);
            box1.setText("Movies");
            DiscoverAdapter.notifyDataSetChanged();
        }

    }

    private void setmyTdiscoveradapter() {
        DiscoverAdapter1 = new DiscoverAdapter1(this, nDshows);
        if(nDshows.length==0){
            Toast.makeText(this,"No Movies / Tv Shows Found",Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            DiscoveryRecyclerView.setAdapter(DiscoverAdapter1);
            DiscoveryRecyclerView.setLayoutManager(new GridLayoutManager(DiscoverResult.this,3));
            avi.smoothToHide();
            avi.setVisibility(View.GONE);
            box1.setText("TV Shows");
            DiscoverAdapter1.notifyDataSetChanged();
        }

    }

}
