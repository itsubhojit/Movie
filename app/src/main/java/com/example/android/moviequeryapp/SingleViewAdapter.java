package com.example.android.moviequeryapp;
import android.printservice.CustomPrinterIconCallback;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.moviequeryapp.models.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SingleViewAdapter extends BaseAdapter {
    private static final String TAG = SingleViewAdapter.class.getSimpleName();
    private Context mContext;
    private List<MovieModel> movieModelList;
    private int resource;

    public SingleViewAdapter(Context c, int gridViewId, List<MovieModel> results) {
       // super(c, gridViewId, );
        mContext = c;
        resource = gridViewId;
        movieModelList = results;


        Log.d(TAG, " : PassedBy#Subhojit -> SingleViewAdapter - CONSTRUCTOR CALLED");
    }

    public int getCount() {
        Log.d(TAG, " : PassedBy#Subhojit -> getCount - CALLED");
        return movieModelList.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, " : PassedBy#Subhojit -> getView - START");


        //View gridView = null;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.d(TAG, " : PassedBy#Subhojit -> SingleViewAdapter - getView getSystemService");

//        @Override public void getView(int position, View convertView, ViewGroup parent) {
//            SquaredImageView view = (SquaredImageView) convertView;
//            if (view == null) {
//                view = new SquaredImageView(context);
//            }
//            String url = getItem(position);
//
//            Picasso.get().load(url).into(view);
//        }
//
        if (convertView == null) {
            //gridView = new View(mContext);
            Log.d(TAG, " : PassedBy#Subhojit -> SingleViewAdapter - getView IF BEFORE INFLATE");

            convertView =inflater.inflate(R.layout.custom_single_grid_view, null);
            Log.d(TAG, " : PassedBy#Subhojit -> SingleViewAdapter - getView IF AFTER INFLATE");
        }

        String imageUrl = (String) getItem(position);

        TextView movieTitle = convertView.findViewById(R.id.gridItem_movieTitle);
        ImageView movieImage = convertView.findViewById(R.id.gridItem_moviePoster);
        TextView textViewData = convertView.findViewById(R.id.gridItem_id);

        textViewData.setText("At no." + (position+1));
        movieTitle.setText(movieModelList.get(position).getTitle());
        Picasso.get().load(imageUrl).into(movieImage);
        //        Picasso.(mContext).load(imageUrl).into(movieImage);

//        It’s constructed using 3 parts:
//
//
//        The base URL will look like: http://image.tmdb.org/t/p/.
//        Then you will need a ‘size’, which will be one of the following: "w92", "w154", "w185", "w342", "w500", "w780", or "original". For most phones we recommend using “w185”.
//        And finally the poster path returned by the query, in this case “/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg”
//
//        Combining these three parts gives us a final url of http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg



        return convertView;
    }
        /*ImageView imageView;
        if (convertView == null) {
            Log.d(TAG, " : PassedBy#Subhojit -> getView - IF");

            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            Log.d(TAG, " : PassedBy#Subhojit -> getView - ELSE");
            imageView = (ImageView) convertView;
        }

        //imageView.setImageResource(mThumbIds[position]);
        imageView.setImageResource(R.drawable.exorcist);
        return imageView;*/

   /*private Context mContext;
   // private BackgroundTask_AsyncTask.AsyncResponse asyncResponse;

    private List<MovieModel> movieModelList;
    private int resource;
    private LayoutInflater inflater;



    public SingleViewAdapter(Context context, int resource, List<MovieModel> movieModelList ) {
        this.mContext = context;
        this.resource = resource;
        this.movieModelList = movieModelList;
    }

    @Override
    public int getCount() {
        return movieModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertView == null){
            gridView = new View(mContext);
            gridView = inflater
                    .inflate(R.layout.custom_single_grid_view, null);

            TextView movieTitle = gridView.findViewById(R.id.gridItem_movieTitle);
            movieTitle.setText(movieModelList.get(position).getTitle());
            //Set image based on selected movie title
            ImageView movieImage = gridView.findViewById(R.id.gridItem_moviePoster);
            movieImage.setImageResource(Integer.parseInt(movieModelList.get(position).getPoster_path()));


        }else{
            gridView = convertView;
        }


        return gridView;
    }*/
}
