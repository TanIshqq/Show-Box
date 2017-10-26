package com.example.lenovo.showbox.Networking;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 26-10-2017.
 */

public class TvShows1 {

    TvShows1.TvShows results[];

    public TvShows1.TvShows[] getResults() {
        return results;
    }

    public void setResults(TvShows1.TvShows[] results1) {
        results = results1;
    }


    public class TvShows {
        public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

        private String original_name;
        @SerializedName("poster_path")
        private String poster;
        @SerializedName("overview")
        private String description;
        @SerializedName("backdrop_path")
        private String backdrop;
        private int id;
        private String first_air_date;
        private float vote_average;
        private String original_language;

        public String getOriginal_name() {
            return original_name;
        }

        public void setOriginal_name(String original_name) {
            this.original_name = original_name;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public void setOriginal_language(String original_language) {
            this.original_language = original_language;
        }

        public String getPoster() {
            return TMDB_IMAGE_PATH + poster;
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

        public String getFirst_air_date() {
            return first_air_date;
        }

        public void setFirst_air_date(String first_air_date) {
            this.first_air_date = first_air_date;
        }

        public float getVote_average() {return vote_average;}

        public void setVote_average(float vote_average) {this.vote_average = vote_average;}


    }
}

