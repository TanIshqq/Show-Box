package com.example.lenovo.showbox.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.showbox.Adapters.Layout_adapter;
import com.example.lenovo.showbox.Adapters.Play_LayoutAdapter;
import com.example.lenovo.showbox.Adapters.TR_LayoutAdapter;
import com.example.lenovo.showbox.Adapters.TvPopularAdapter;
import com.example.lenovo.showbox.Adapters.Tv_Latest_Adapter;
import com.example.lenovo.showbox.Adapters.Tv_OnAir_Adapter;
import com.example.lenovo.showbox.Adapters.Tv_TR_Adapter;
import com.example.lenovo.showbox.Adapters.UpMo_LayoutAdapter;
import com.example.lenovo.showbox.Networking.Movies1;
import com.example.lenovo.showbox.Networking.Services;
import com.example.lenovo.showbox.Networking.TvShows1;
import com.example.lenovo.showbox.Networking.apiClient;
import com.example.lenovo.showbox.R;
import com.wang.avi.AVLoadingIndicatorView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    SearchView simpleSearchView;
    RecyclerView mRecyclerView;
    RecyclerView UMRecyclerView;
    RecyclerView TRRecyclerView;
    RecyclerView PLRecyclerView;
    UpMo_LayoutAdapter UMAdapter;
    TR_LayoutAdapter TRAdapter;
    Play_LayoutAdapter PLAdapter;
    Layout_adapter mAdapter;
    TvPopularAdapter nAdapter;
    Tv_Latest_Adapter nLAdapter;
    Tv_OnAir_Adapter nOAdapter;
    Tv_TR_Adapter nTRAdapter;
    private AVLoadingIndicatorView avi;
    Movies1.Movies movies[] ;
    TvShows1.TvShows nshows[];
    TvShows1.TvShows nLshows[];
    TvShows1.TvShows nOAshows[];
    TvShows1.TvShows nTRshows[];
    Movies1.Movies UMmovies[];
    Movies1.Movies TRmovies[];
    Movies1.Movies PLmovies[];
    TextView box1;
    TextView box2;
    TextView box3;
    TextView box4;
    ImageView imageView;

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
                    imageView = (ImageView)findViewById(R.id.imageView);
                    imageView.setImageResource(R.drawable.collage);
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
                    box1 = (TextView)findViewById(R.id.box1);
                    box2 = (TextView)findViewById(R.id.box2);
                    box3 = (TextView)findViewById(R.id.box3);
                    box4 = (TextView)findViewById(R.id.box4);
                    imageView = (ImageView)findViewById(R.id.imageView);
                    imageView.setImageResource(R.drawable.collage1);
                    avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
                    Services service1 = apiClient.getClient().create(Services.class);
                    Call<TvShows1> TvResponseCall = service1.getPopularTvShows();
                    Call<TvShows1> TvResponseCall1 = service1.getTodaysShows();
                    Call<TvShows1> TvResponseCall2 = service1.getTopRatedTvShows();
                    Call<TvShows1> TvResponseCall3 = service1.getOnAirTvShows();

                    avi.setVisibility(View.VISIBLE);
                    avi.smoothToShow();

                    TvResponseCall.enqueue(new Callback<TvShows1>() {
                        @Override
                        public void onResponse(Call<TvShows1> call, Response<TvShows1> response) {
                            TvShows1 tvshows1 = response.body();
                            nshows = tvshows1.getResults();
                            setmyTvadapter();

                        }

                        @Override
                        public void onFailure(Call<TvShows1> call, Throwable t) {

                        }
                    });

                    TvResponseCall1.enqueue(new Callback<TvShows1>() {
                        @Override
                        public void onResponse(Call<TvShows1> call, Response<TvShows1> response) {
                            TvShows1 tvshows1 = response.body();
                            nLshows = tvshows1.getResults();
                            setnLTvadapter();

                        }

                        @Override
                        public void onFailure(Call<TvShows1> call, Throwable t) {

                        }
                    });

                    TvResponseCall2.enqueue(new Callback<TvShows1>() {
                        @Override
                        public void onResponse(Call<TvShows1> call, Response<TvShows1> response) {
                            TvShows1 tvshows1 = response.body();
                            nTRshows = tvshows1.getResults();
                            setnTRTvadapter();

                        }

                        @Override
                        public void onFailure(Call<TvShows1> call, Throwable t) {

                        }
                    });

                    TvResponseCall3.enqueue(new Callback<TvShows1>() {
                        @Override
                        public void onResponse(Call<TvShows1> call, Response<TvShows1> response) {
                            TvShows1 tvshows1 = response.body();
                            nOAshows = tvshows1.getResults();
                            setnOATvadapter();

                        }

                        @Override
                        public void onFailure(Call<TvShows1> call, Throwable t) {

                        }
                    });

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
        box1.setText("Popular Movies");
        mAdapter.notifyDataSetChanged();
    }

    private void UMsetmyadapter() {
        UMAdapter = new UpMo_LayoutAdapter(this,UMmovies);
        UMRecyclerView.setAdapter(UMAdapter);
        UMRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false));
        box2.setText("Upcoming Movies");
        UMAdapter.notifyDataSetChanged();
    }

    private void TRsetmyadapter() {
        TRAdapter = new TR_LayoutAdapter(this,TRmovies);
        TRRecyclerView.setAdapter(TRAdapter);
        TRRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false));
        box3.setText("Top Rated Movies");
        TRAdapter.notifyDataSetChanged();
    }

    private void PLsetmyadapter() {
        PLAdapter = new Play_LayoutAdapter(this,PLmovies);
        PLRecyclerView.setAdapter(PLAdapter);
        PLRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false));
        avi.smoothToHide();
        avi.setVisibility(View.GONE);
        box4.setText("Now Playing Movies");
        PLAdapter.notifyDataSetChanged();
    }

    private void setmyTvadapter() {
        nAdapter = new TvPopularAdapter(this,nshows);
        mRecyclerView.setAdapter(nAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false));
        box1.setText("Popular Shows");
        nAdapter.notifyDataSetChanged();
    }

    private void setnLTvadapter() {
        nLAdapter = new Tv_Latest_Adapter(this,nLshows);
        UMRecyclerView.setAdapter(nLAdapter);
        UMRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false));
        box2.setText("Screenings Today");
        nLAdapter.notifyDataSetChanged();
    }

    private void setnTRTvadapter() {
        nTRAdapter = new Tv_TR_Adapter(this,nTRshows);
        TRRecyclerView.setAdapter(nTRAdapter);
        TRRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false));
        box3.setText("Top Rated Shows");
        nTRAdapter.notifyDataSetChanged();
    }

    private void setnOATvadapter() {
        nOAdapter = new Tv_OnAir_Adapter(this,nOAshows);
        PLRecyclerView.setAdapter(nOAdapter);
        PLRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false));
        avi.smoothToHide();
        avi.setVisibility(View.GONE);
        box4.setText("On Air Shows");
        nOAdapter.notifyDataSetChanged();
    }

    public static boolean isNetworkStatusAvialable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
                if(netInfos.isConnected())
                    return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!isNetworkStatusAvialable (getApplicationContext())) {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        } else {

            mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
            UMRecyclerView = (RecyclerView)findViewById(R.id.recyclerView1);
            TRRecyclerView = (RecyclerView)findViewById(R.id.recyclerView2);
            PLRecyclerView = (RecyclerView)findViewById(R.id.recyclerView3);

            box1 = (TextView)findViewById(R.id.box1);
            box2 = (TextView)findViewById(R.id.box2);
            box3 = (TextView)findViewById(R.id.box3);
            box4 = (TextView)findViewById(R.id.box4);
            imageView = (ImageView)findViewById(R.id.imageView);
            imageView.setImageResource(R.drawable.collage);
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



            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            simpleSearchView = (SearchView) findViewById(R.id.search);
            CharSequence query = simpleSearchView.getQuery();
            simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Intent intent = new Intent(MainActivity.this,Search_Activity.class);
                    Bundle b = new Bundle();
                    b.putString("Query",query);
                    intent.putExtras(b);
                    startActivity(intent);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }

            });




        }

    }

}
