package com.example.lenovo.showbox.Networking;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 22-10-2017.
 */

public class Movies1{

    Movies results[];

    public Movies[] getresults() {
        return results;
    }

    public void setresults(Movies[] results1) {
        results = results1;
    }


    public class Movies {
        public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

        private String title;
        @SerializedName("poster_path")
        private String poster;
        @SerializedName("overview")
        private String description;
        @SerializedName("backdrop_path")
        private String backdrop;
        private int id;
        private String release_date;
        private boolean adult;
        private float vote_average;

        public Movies() {
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPoster() {
            if(poster==null){
                return null;
            }
            else{
                return TMDB_IMAGE_PATH + poster;
            }

        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getBackdrop() {
            return TMDB_IMAGE_PATH  + backdrop;
        }

        public void setBackdrop(String backdrop) {
            this.backdrop = backdrop;
        }

        public int getId() {return id;}

        public void setId(int id) {this.id = id;}

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public float getVote_average() {return vote_average;}

        public void setVote_average(float vote_average) {this.vote_average = vote_average;}

        public Boolean getAdult() {return adult;}

        public void setAdult(Boolean adult) {this.adult = adult;}

    }
}

