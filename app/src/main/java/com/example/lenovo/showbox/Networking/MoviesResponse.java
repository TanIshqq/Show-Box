package com.example.lenovo.showbox.Networking;

import com.example.lenovo.showbox.Networking.Movies1;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Lenovo on 23-10-2017.
 */

public class MoviesResponse {

    @SerializedName("results")
    private ArrayList<Movies1.Movies> results;

    public ArrayList<Movies1.Movies> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movies1.Movies> results) {
        this.results = results;
    }


}
