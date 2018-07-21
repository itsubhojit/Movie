package com.example.android.moviequeryapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.android.moviequeryapp.models.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<MovieModel> movie_list;
    public CustomAdapter(Context context, ArrayList<MovieModel> movie_list) {
        mContext = context;
        this.movie_list = movie_list;
    }
    @Override
    public int getCount() {
        return movie_list.size();
    }
    @Override
    public Object getItem(int position) {

        return movie_list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(500, 600));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(5, 5, 5, 5);
            Picasso.get().load("http://image.tmdb.org/t/p/w342//" + movie_list.get(position).getPoster_path()).into(imageView);
        } else {
            imageView = (ImageView) convertView;
        }
        //      imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    public void updateMovies(ArrayList<MovieModel> movie_list) {
        this.movie_list= movie_list;
    }
}
