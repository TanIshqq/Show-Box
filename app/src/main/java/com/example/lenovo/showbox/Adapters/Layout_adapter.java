package com.example.lenovo.showbox.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.showbox.Networking.Movies1;
import com.example.lenovo.showbox.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Lenovo on 22-10-2017.
 */
 public class Layout_adapter extends RecyclerView.Adapter<Layout_adapter.MovieViewHolder> {

        private Context mContext;
        private Movies1.Movies mMovies[];
        private MovieClickListener mListener;
        int Movie_ID;

        @Override
        public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.row_layout,parent,false);
            return new MovieViewHolder(itemView,mListener);
        }

        @Override
        public void onBindViewHolder(MovieViewHolder holder, final int position) {

            final Movies1.Movies movie = mMovies[position];
           // Movie_ID = movie.getId();
            holder.titleTextView.setText(movie.getTitle());
            holder.dateTextView.setText(movie.getRelease_date());
            if(movie.getAdult()==false){
                holder.adultTextView.setText("U/A");
            }
            else{
                holder.adultTextView.setText("ADULT");
            }
            holder.voteTextView.setText(movie.getVote_average() + "");
            Picasso.with(mContext).load(movie.getPoster()).resize(2300, 1000).centerInside().into(holder.poster);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("TAG", "onClick: "+movie.getId());
                    mListener.onItemClick(v,position,movie.getId());

                }
            });


        }

        @Override
        public int getItemCount() {
            return mMovies.length;
        }

        public interface MovieClickListener {
            void onItemClick(View view,int position, int id);

        }


        public Layout_adapter(Context context,Movies1.Movies movies1[], MovieClickListener mListener){
            mContext = context;
            this.mMovies = movies1;
            this.mListener = mListener;
        }

        public static class MovieViewHolder extends RecyclerView.ViewHolder {

            TextView titleTextView;
            TextView voteTextView;
            TextView adultTextView;
            TextView dateTextView;
            ImageView poster;
            MovieClickListener mMovieClickListener;

            public MovieViewHolder(View itemView,MovieClickListener listener) {
                super(itemView);
                mMovieClickListener = listener;
                titleTextView = (TextView) itemView.findViewById(R.id.title);
                voteTextView = (TextView) itemView.findViewById(R.id.vote);
                adultTextView = (TextView) itemView.findViewById(R.id.adult);
                dateTextView = (TextView) itemView.findViewById(R.id.date);
                poster = (ImageView) itemView.findViewById(R.id.poster);
            }
/*
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    mMovieClickListener.onItemClick(view,position,Movie_ID);

                }

            }*/
        }
 }

