package com.example.lenovo.showbox;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wang.avi.AVLoadingIndicatorView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView UMRecyclerView;
    RecyclerView TRRecyclerView;
    RecyclerView PLRecyclerView;
    UpMo_LayoutAdapter UMAdapter;
    TR_LayoutAdapter TRAdapter;
    Play_LayoutAdapter PLAdapter;
    private Layout_adapter mAdapter;
    private AVLoadingIndicatorView avi;
    Movies1.Movies movies[] ;
    Movies1.Movies UMmovies[];
    Movies1.Movies TRmovies[];
    Movies1.Movies PLmovies[];
    TextView box1;
    TextView box2;
    TextView box3;
    TextView box4;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_movies:
                    box1 = (TextView)findViewById(R.id.box1);
                    box2 = (TextView)findViewById(R.id.box2);
                    box3 = (TextView)findViewById(R.id.box3);
                    box4 = (TextView)findViewById(R.id.box4);
                    avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
                    Services service = apiClient.getClient().create(Services.class);

                    Call<Movies1> moviesResponseCall = service.getMovies();
                    Call<Movies1> moviesResponseCall1 = service.getUpcomingMovies();
                    Call<Movies1> moviesResponseCall2 = service.getTopRatedMovies();
                    Call<Movies1> moviesResponseCall3 = service.getPlayingMovies();
                    avi.setVisibility(View.VISIBLE);
                    avi.smoothToShow();

                    moviesResponseCall.enqueue(new Callback<Movies1>() {
                        @Override
                        public void onResponse(Call<Movies1> call, Response<Movies1> response) {
                            Movies1 movies1 = response.body();
                            movies = movies1.getresults();
                            setmyadapter();

                        }

                        @Override
                        public void onFailure(Call<Movies1> call, Throwable t) {

                        }
                    });

                    moviesResponseCall1.enqueue(new Callback<Movies1>() {
                        @Override
                        public void onResponse(Call<Movies1> call, Response<Movies1> response) {
                            Movies1 movies1 = response.body();
                            UMmovies = movies1.getresults();
                            UMsetmyadapter();

                        }

                        @Override
                        public void onFailure(Call<Movies1> call, Throwable t) {

                        }
                    });

                    moviesResponseCall2.enqueue(new Callback<Movies1>() {
                        @Override
                        public void onResponse(Call<Movies1> call, Response<Movies1> response) {
                            Movies1 movies1 = response.body();
                            TRmovies = movies1.getresults();
                            TRsetmyadapter();

                        }

                        @Override
                        public void onFailure(Call<Movies1> call, Throwable t) {

                        }
                    });

                    moviesResponseCall3.enqueue(new Callback<Movies1>() {
                        @Override
                        public void onResponse(Call<Movies1> call, Response<Movies1> response) {
                            Movies1 movies1 = response.body();
                            PLmovies = movies1.getresults();
                            PLsetmyadapter();

                        }

                        @Override
                        public void onFailure(Call<Movies1> call, Throwable t) {

                        }
                    });


                    return true;

                case R.id.navigation_shows:
//                    mTextMessage.setText(R.string.title_shows);
                    return true;
                case R.id.navigation_favourites:
//                    mTextMessage.setText(R.string.title_favourites);
                    return true;
            }
            return false;
        }

    };

    private void setmyadapter() {
        mAdapter = new Layout_adapter(this,movies);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false));
        box1.setText("Popular");
        mAdapter.notifyDataSetChanged();
    }

    private void UMsetmyadapter() {
        UMAdapter = new UpMo_LayoutAdapter(this,UMmovies);
        UMRecyclerView.setAdapter(UMAdapter);
        UMRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false));
        box2.setText("Upcoming");
        UMAdapter.notifyDataSetChanged();
    }

    private void TRsetmyadapter() {
        TRAdapter = new TR_LayoutAdapter(this,TRmovies);
        TRRecyclerView.setAdapter(TRAdapter);
        TRRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false));
        box3.setText("Top Rated");
        TRAdapter.notifyDataSetChanged();
    }

    private void PLsetmyadapter() {
        PLAdapter = new Play_LayoutAdapter(this,PLmovies);
        PLRecyclerView.setAdapter(UMAdapter);
        PLRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false));
        avi.smoothToHide();
        avi.setVisibility(View.GONE);
        box4.setText("Now Playing");
        PLAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        UMRecyclerView = (RecyclerView)findViewById(R.id.recyclerView1);
        TRRecyclerView = (RecyclerView)findViewById(R.id.recyclerView2);
        PLRecyclerView = (RecyclerView)findViewById(R.id.recyclerView3);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
