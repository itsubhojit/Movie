package com.example.android.moviequeryapp;
import android.graphics.Color;
import android.printservice.CustomPrinterIconCallback;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.moviequeryapp.models.MovieModel;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class SingleViewAdapter extends BaseAdapter {
    private static final String TAG = SingleViewAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<MovieModel> movieModelList;
    private LayoutInflater inflater;


    public SingleViewAdapter(Context c, ArrayList<MovieModel> movieList) {
        mContext = c;
        movieModelList = movieList;
        inflater = LayoutInflater.from(c);
        //movieModelList = new ArrayList<>();

        Log.d(TAG, " : PassedBy#Subhojit -> SingleViewAdapter - CONSTRUCTOR CALLED");
    }

    public int getCount() {
        if(movieModelList.size() == 0)
            return 0;
        else
           // return movieModelList.size();
        Log.d(TAG, " : PassedBy#Subhojit -> getCount - CALLED");
        return movieModelList.size();
    }

    public Object getItem(int position) {
        return movieModelList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View customView;
        TextView movieTitle;
        ImageView movieImage;
        String imageUrl = "http://image.tmdb.org/t/p/w342//";

        Log.d(TAG, " : PassedBy#Subhojit -> getView - START");
        //LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.d(TAG, " : PassedBy#Subhojit -> SingleViewAdapter - getView getSystemService");
        //GridView gridView = convertView.findViewById(R.id.gridViewId);

        if (convertView == null) {
            customView = new View(mContext);
            Log.d(TAG, " : PassedBy#Subhojit -> SingleViewAdapter - getView IF BEFORE INFLATE");
            customView = inflater.inflate(R.layout.single_image_text_view, parent, false);
            Log.d(TAG, " : PassedBy#Subhojit -> SingleViewAdapter - getView IF AFTER INFLATE");

        }else{
            customView = convertView;
        }
        movieTitle = customView.findViewById(R.id.gridItem_movieTitle);
        movieImage = customView.findViewById(R.id.gridItem_moviePoster);
        TextView itemNumber = customView.findViewById(R.id.gridItem_id);
        if(position %2 == 1)
        {
            // Set a background color for List / Grid View regular row/item
            movieImage.setBackgroundColor(Color.parseColor("#302C2C"));
        }
        else {
            // Set the background color for alternate row/item
            movieImage.setBackgroundColor(Color.parseColor("#626B65"));
        }
        //System.out.print(position + " Subhojit Halder TAG");
        itemNumber.setText("At no." + (position+1));
        movieTitle.setText(movieModelList.get(position).getTitle());
        // Image Resolutions : "w92", "w154", "w185", "w342", "w500", "w780", or "original"
        Picasso.get().load(imageUrl + movieModelList.get(position).getPoster_path()).into(movieImage);

        return customView;
    }

    public void updateMovies(ArrayList<MovieModel> items){
        this.movieModelList = items;
    }
}
