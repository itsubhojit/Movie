package com.example.android.moviequeryapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.util.Log;
import com.example.android.moviequeryapp.models.MovieModel;

import org.json.JSONException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainGridActivity extends AppCompatActivity {

    private static final String TAG = MainGridActivity.class.getSimpleName();

/*
        BackgroundTask_AsyncTask asyncTask = new BackgroundTask_AsyncTask(new BackgroundTask_AsyncTask.AsyncResponse() {
            @Override
            public void processFinish(List<MovieModel> output) {
                ImageAdapter imageAdapter = new ImageAdapter(this, R.layout.custom_grid_view_layout, output);
                movieItems.setAdapter(imageAdapter);
            }
        });
*/

        private GridView movieItems;
        public  TextView textErrorMessage;
        private  ProgressBar loading;
        private  TextView textRawData;
        private  TextView mData;
        private SingleViewAdapter singleViewAdapterInstance;
        private CustomAdapter customAdapter;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //TAG is here..
            System.out.println("TAG = " + TAG);
            Log.d(TAG, " : PassedBy#Subhojit -> onCreate - START");
            setContentView(R.layout.activity_main_grid);
            movieItems = findViewById(R.id.gridViewId);

            singleViewAdapterInstance = new SingleViewAdapter(getApplicationContext(), new ArrayList<MovieModel>());
            movieItems.setAdapter(singleViewAdapterInstance);

            /*customAdapter = new CustomAdapter(this,new ArrayList<MovieModel>());
            movieItems.setAdapter(customAdapter);*/

            textRawData = findViewById(R.id.tv_test);
            mData = findViewById(R.id.tv_Data);
            textErrorMessage = findViewById(R.id.tv_error_message);
            loading = findViewById(R.id.loadingIndecator);
            loadDiscoveredMovies();
            Log.d(TAG, " : PassedBy#Subhojit -> onCreate - FINISH");
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.settings, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            switch(id){
                case R.id.popular_movies:
                    resetGridView();
                    loadPopularMovies();
                    return true;

                case R.id.top_rated_movies:
                    resetGridView();
                    loadTopRatedMovies();
                    return true;

                case R.id.refresh:
                    resetGridView();
                    loadDiscoveredMovies();
                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }
        }

        public void loadPopularMovies(){
            String x = "084c79c7722ce9496963780c61fa46a1";
            URL url = NetworkUtils.buildPopularMovieUrl(x);
            textRawData.setText(url.toString());
            new BackgroundTask_AsyncTask().execute(url);
            Log.d(TAG, " : PassedBy#Subhojit");
            showMovie();
        }

        public void loadTopRatedMovies(){
            String x = "";
            URL url = NetworkUtils.buildTopRatedMovieUrl(x);
            textRawData.setText(url.toString());
            new BackgroundTask_AsyncTask().execute(url);
            showMovie();
        }

        public void loadDiscoveredMovies(){
            String x = "084c79c7722ce9496963780c61fa46a1";
            URL url = NetworkUtils.buildDiscoverMovieUrl(x);
            Log.d(TAG, " : PassedBy#Subhojit -> loadDiscoveredMovies - buildDiscoverMovieUrl");
            textRawData.setText(url.toString());
            Log.d(TAG, " : PassedBy#Subhojit -> BackgroundTask_AsyncTask() - START");
            new BackgroundTask_AsyncTask().execute(url);
            Log.d(TAG, " : PassedBy#Subhojit -> BackgroundTask_AsyncTask() - FINISH");
            showMovie();
        }

        public void showMovie(){
            textErrorMessage.setVisibility(View.INVISIBLE);
            movieItems.setVisibility(View.VISIBLE);
            Log.d(TAG, " : PassedBy#Subhojit -> showMovie(){}");
        }

        public void showErrorMessage(){
            textErrorMessage.setVisibility(View.VISIBLE);
            movieItems.setVisibility(View.INVISIBLE);
            Log.d(TAG, " : PassedBy#Subhojit -> showErrorMessage(){}");
        }

        public void resetGridView(){

        }

        public void exitNow(){

        }
//======================================================================================
//        @Override
//        public void onGridItemClick(int clickedItemIndex) {
//
//        }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_grid);
//
//
//    //=============================================================================//

public class BackgroundTask_AsyncTask extends AsyncTask<URL, String, ArrayList<MovieModel>> {

//    public AsyncResponse delegate = null;
//
//    public interface AsyncResponse {
//        void processFinish(List<MovieModel> output);
//    }

//    public BackgroundTask_AsyncTask(AsyncResponse delegate) {
//        this.delegate = delegate;
//    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loading.setVisibility(View.VISIBLE);
        Log.d(TAG, " : PassedBy#Subhojit -> onPreExecute(){}");
    }

    protected ArrayList<MovieModel> doInBackground(URL... urls) {
        Log.d(TAG, " : PassedBy#Subhojit -> doInBackground - START");

        if (urls.length == 0) {
            return null;
        }

        URL searchUrl = urls[0];
        URL url;

        try {
            url = searchUrl;
            String rawJsonDataFromServer = NetworkUtils.getResponseFromHttpUrl(url);
            Log.d(TAG, " : PassedBy#Subhojit -> doInBackground - AFTER GETTING JSON RAW DATA");

            Log.d(TAG, " : PassedBy#Subhojit -> doInBackground - BEFORE List<MovieModel> convertedJsonData");
            ArrayList<MovieModel> convertedJsonData = ConvertJsonUnits.getConvertedSimpleJsonData(rawJsonDataFromServer);
            Log.d(TAG, " : PassedBy#Subhojit -> doInBackground - AFTER SUCCESS!!!  List<MovieModel> convertedJsonData");
            Log.d(TAG, " : PassedBy#Subhojit -> doInBackground - RETURN JSON DATA");
            return convertedJsonData;

        } catch (JSONException x){
            x.printStackTrace();
            Log.d(TAG, " : PassedBy#Subhojit -> doInBackground - JSON EXCEPTION CATCHES");

            return null;
        } catch (Exception e) {
            Log.d(TAG, " : PassedBy#Subhojit -> doInBackground - EXCEPTION CATCHED");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<MovieModel> results) {
        Log.d(TAG, " : PassedBy#Subhojit -> onPostExecute - START");
        super.onPostExecute(results);
        loading.setVisibility(View.INVISIBLE);
        if(results != null){
            Log.d(TAG, " : PassedBy#Subhojit -> onPostExecute - IF-START");
//            customAdapter.updateMovies(results);
//            customAdapter.notifyDataSetChanged();
            singleViewAdapterInstance.updateMovies(results);
            singleViewAdapterInstance.notifyDataSetChanged();
//            singleViewCreate = new SingleViewAdapter(getApplicationContext(),R.id.gridViewId);
           // movieItems.setAdapter(singleViewAdapterInstance);
            Log.d(TAG, " : PassedBy#Subhojit -> onPostExecute - IF-> SetAdapter Done >>> SUCCESS");

        }else{
            showErrorMessage();
            Log.d(TAG, " : PassedBy#Subhojit -> onPostExecute - ELSE");
        }

        Log.d(TAG, " : PassedBy#Subhojit -> onPostExecute - END");
    }
}
}
