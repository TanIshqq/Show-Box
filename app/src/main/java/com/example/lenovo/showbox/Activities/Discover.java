package com.example.lenovo.showbox.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lenovo.showbox.R;

import java.util.ArrayList;
import java.util.List;

public class Discover extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private Spinner spinner1, spinner2, spinner3,spinner4,spinner5,spinner6;
    private Button submit;
    String category,language,adult,sortby,ratings,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        spinner1 = (Spinner) findViewById(R.id.category);
        spinner1.setOnItemSelectedListener(this);
        spinner2 = (Spinner) findViewById(R.id.language);
        spinner2.setOnItemSelectedListener(this);
        spinner3 = (Spinner) findViewById(R.id.ratings);
        spinner3.setOnItemSelectedListener(this);
        spinner4 = (Spinner) findViewById(R.id.adult);
        spinner4.setOnItemSelectedListener(this);
        spinner5 = (Spinner) findViewById(R.id.year);
        spinner5.setOnItemSelectedListener(this);
        spinner6 = (Spinner) findViewById(R.id.sortby);
        spinner6.setOnItemSelectedListener(this);

        submit = (Button) findViewById(R.id.submit);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.spinner_language, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.spinner_ratings, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.spinner_adult, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter3);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.spinner_year, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter4);

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.spinner_sortby, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter5);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        category = String.valueOf(spinner1.getSelectedItem());
        language = String.valueOf(spinner2.getSelectedItem());
        ratings = String.valueOf(spinner3.getSelectedItem());
        adult = String.valueOf(spinner4.getSelectedItem());
        year = String.valueOf(spinner5.getSelectedItem());
        sortby = String.valueOf(spinner6.getSelectedItem());


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void Done(View view){
        Animation animation = AnimationUtils.loadAnimation(Discover.this,R.anim.rotate);
        animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(Discover.this,DiscoverResult.class);
                        Bundle b = new Bundle();
                        b.putString("Category",category);
                        b.putString("Language",language);
                        b.putString("Ratings",ratings);
                        b.putString("Adult",adult);
                        b.putString("Year",year);
                        b.putString("SortBy",sortby);
                        intent.putExtras(b);
                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
        submit.startAnimation(animation);
    }

}