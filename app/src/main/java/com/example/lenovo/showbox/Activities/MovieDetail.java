package com.example.lenovo.showbox.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.showbox.Networking.Movie_Detail;
import com.example.lenovo.showbox.Networking.Services;
import com.example.lenovo.showbox.Networking.apiClient;
import com.example.lenovo.showbox.R;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetail extends AppCompatActivity {
    ImageView backdrop;
    TextView vote;
    TextView decription;
    TextView adult;
    TextView date;
    TextView title;
    int movie_Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        backdrop = (ImageView)findViewById(R.id.detailbackdrop);
        vote = (TextView)findViewById(R.id.detailvote);
        decription = (TextView)findViewById(R.id.detaildescription);
        adult = (TextView)findViewById(R.id.detailadult);
        date = (TextView)findViewById(R.id.detaildate);
        title = (TextView)findViewById(R.id.detailtitle);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        movie_Id = b.getInt("Movie_ID");
        Log.i("TAG", "onCreate: "+movie_Id);
        Services service = apiClient.getClient().create(Services.class);
        Call<Movie_Detail> detailResponseCall = service.getDeatilsMovie(movie_Id);



        detailResponseCall.enqueue(new Callback<Movie_Detail>() {
            @Override
            public void onResponse(Call<Movie_Detail> call, Response<Movie_Detail> response) {
                response.body();
                Picasso.with(MovieDetail.this).load(response.body().getBackdrop()).resize(1400, 600).centerInside().into(backdrop);
                decription.setText(response.body().getDescription());
                vote.setText(response.body().getVote_average()+"");
                title.setText(response.body().getTitle());
                date.setText(response.body().getRelease_date());
                if(response.body().getAdult()==false){
                    adult.setText("U/A");
                }
                else{
                    adult.setText("ADULT");
                }



            }

            @Override
            public void onFailure(Call<Movie_Detail> call, Throwable t) {

            }
        });




    }
}
