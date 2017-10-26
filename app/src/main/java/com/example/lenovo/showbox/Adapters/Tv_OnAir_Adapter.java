package com.example.lenovo.showbox.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.showbox.Networking.TvShows1;
import com.example.lenovo.showbox.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Lenovo on 27-10-2017.
 */

public class Tv_OnAir_Adapter extends RecyclerView.Adapter<Tv_OnAir_Adapter.TvViewHolder> {

    private Context mContext;
    private TvShows1.TvShows mTvShows[];
    private Tv_OnAir_Adapter.MovieClickListener mListener;

    @Override
    public Tv_OnAir_Adapter.TvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.row_layout,parent,false);
        return new Tv_OnAir_Adapter.TvViewHolder(itemView,mListener);
    }

    @Override
    public void onBindViewHolder(Tv_OnAir_Adapter.TvViewHolder holder, int position) {

        TvShows1.TvShows TvShows = mTvShows[position];
        holder.titleTextView.setText(TvShows.getOriginal_name());
        holder.dateTextView.setText(TvShows.getFirst_air_date());
        holder.voteTextView.setText(TvShows.getVote_average() + "");
        holder.adultTextView.setText(TvShows.getOriginal_language());
        Picasso.with(mContext).load(TvShows.getPoster()).resize(2300, 1000).centerInside().into(holder.poster);


    }

    @Override
    public int getItemCount() {
        return mTvShows.length;
    }

    public interface MovieClickListener {
        void onItemClick(View view,int position);
        void onRemoveClicked(int position);
    }


    public Tv_OnAir_Adapter(Context context,TvShows1.TvShows TvShows1[]){
        mContext = context;
        this.mTvShows = TvShows1;
    }

    public static class TvViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView titleTextView;
        TextView voteTextView;
        TextView dateTextView;
        TextView adultTextView;
        ImageView poster;
        Tv_OnAir_Adapter.MovieClickListener mMovieClickListener;

        public TvViewHolder(View itemView,Tv_OnAir_Adapter.MovieClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            mMovieClickListener = listener;
            titleTextView = (TextView) itemView.findViewById(R.id.title);
            voteTextView = (TextView) itemView.findViewById(R.id.vote);
            dateTextView = (TextView) itemView.findViewById(R.id.date);
            adultTextView = (TextView) itemView.findViewById(R.id.adult);
            poster = (ImageView) itemView.findViewById(R.id.poster);
        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                if(id == R.id.recyclerView){
                    mMovieClickListener.onItemClick(view,position);
                }
            }

        }
    }
}

