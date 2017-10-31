package com.example.lenovo.showbox.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.lenovo.showbox.R;

import java.util.ArrayList;
import java.util.List;

public class Discover extends AppCompatActivity {


    private Spinner spinner1, spinner2, spinner3;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        spinner1 = (Spinner) findViewById(R.id.category);
        spinner2 = (Spinner) findViewById(R.id.language);
        spinner3 = (Spinner) findViewById(R.id.ratings);
        submit = (Button) findViewById(R.id.submit);


        public void addItemsOnSpinner1() {

            List<String> list = new ArrayList<String>();
            list.add("Movies");
            list.add("Tv Shows");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner1.setAdapter(dataAdapter);
        }

        public void addItemsOnSpinner2(){

            List<String> list = new ArrayList<String>();
            list.add("English");
            list.add("Hindi");
            list.add("French");
            list.add("Japanese");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(dataAdapter);
        }

        public void addItemsOnSpinner3(){

            List<String> list = new ArrayList<String>();
            list.add("5.0");
            list.add("6.0");
            list.add("7.0");
            list.add("8.0");
            list.add("9.0");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner3.setAdapter(dataAdapter);
        }


//                            "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) +
//                            "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem()),


        Animation animation = AnimationUtils.loadAnimation(Discover.this, R.anim.rotate);



    }
}