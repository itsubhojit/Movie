package com.example.android.moviequeryapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android.moviequeryapp.models.MovieModel;

import java.util.ArrayList;
import java.util.List;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ImageAdapter {

/*
    private Context mContext;
    private List<MovieModel> movieModelList;
    private int layoutResourceId;
    private LayoutInflater inflater;


    public ImageAdapter (Context context, int resource, List<MovieModel> movieModelList){

        this.mContext = context;
            this.layoutResourceId = resource;
            //this.movieModelList = movieModelList;
            inflater = (LayoutInflater) mContext
                    .getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount () {
            return movieModelList.size();
        }

        @Override
        public Object getItem ( int i){
            return null;
        }

        @Override
        public long getItemId ( int i){
            return 0;
        }

        @Override
        public View getView ( int i, View convertView, ViewGroup parent){
           // View gridViewAndroid;
            if (convertView == null) {
               // gridViewAndroid = new View(mContext);
                convertView = inflater.inflate(R.layout.custom_single_grid_view,null);
                //
            }
            TextView movieTitle = convertView.findViewById(R.id.gridItem_movieTitle);
            ImageView moviePoster = convertView.findViewById(R.id.gridItem_moviePoster);

            movieTitle.setText(movieModelList.get(i).getTitle());
            //moviePoster.setImageResource();
            return convertView;
        }*/

    }
