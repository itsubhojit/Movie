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
import java.util.List;

public class MainGridActivity extends AppCompatActivity {

    private static final String TAG = MainGridActivity.class.getSimpleName();

 //  BackgroundTask_AsyncTask asyncTask = new BackgroundTask_AsyncTask(new BackgroundTask_AsyncTask.AsyncResponse() {
//            @Override
//            public void processFinish(List<MovieModel> output) {
//                ImageAdapter imageAdapter = new ImageAdapter(this, R.layout.custom_grid_view_layout, output);
//                movieItems.setAdapter(imageAdapter);
//            }
//        });

        private GridView movieItems;
        public static TextView textErrorMessage;
        private static ProgressBar loading;
        private static TextView textRawData;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //TAG is here..
            System.out.println("TAG = " + TAG);
            Log.d(TAG, " : PassedBy#Subhojit -> onCreate - START");
            setContentView(R.layout.activity_main_grid);
            movieItems = findViewById(R.id.gridViewId);
            textRawData = findViewById(R.id.tv_test);
            movieItems = findViewById(R.id.gridViewId);
            textErrorMessage = findViewById(R.id.tv_error_message);
            loading = findViewById(R.id.loadingIndecator);
            loadDiscoveredMovies();
            //loadPopularMovies();
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

            String x = "api_key";
            URL url = NetworkUtils.buildPopularMovieUrl(x);
            textRawData.setText(url.toString());
            new BackgroundTask_AsyncTask().execute(url);
            Log.d(TAG, " : PassedBy#Subhojit");
            showMovie();
        }

        public void loadTopRatedMovies(){
            //resetGridView();
            String x = "api_key";
            URL url = NetworkUtils.buildTopRatedMovieUrl(x);
            textRawData.setText(url.toString());
            new BackgroundTask_AsyncTask().execute(url);
            showMovie();
        }

        public void loadDiscoveredMovies(){

            String x = "apy_key";
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
            //movieItems.removeAllViews();
//            LinearLayout linearLayout = findViewById(R.id.linearLayout);
//            linearLayout.removeAllViews();

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

public class BackgroundTask_AsyncTask extends AsyncTask<URL, String, List<MovieModel>> {

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

    protected List<MovieModel> doInBackground(URL... urls) {
        Log.d(TAG, " : PassedBy#Subhojit -> doInBackground - START");

        if (urls.length == 0) {
            return null;
        }

        URL searchUrl = urls[0];
        URL url=null;

        try {
            url = searchUrl;
            String rawJsonDataFromServer = NetworkUtils.getResponseFromHttpUrl(url);
            Log.d(TAG, " : PassedBy#Subhojit -> doInBackground - AFTER GETTING JSON RAW DATA");

            Log.d(TAG, " : PassedBy#Subhojit -> doInBackground - BEFORE List<MovieModel> convertedJsonData");
            List<MovieModel> convertedJsonData = ConvertJsonUnits.getConvertedSimpleJsonData(rawJsonDataFromServer);
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
    protected void onPostExecute(List<MovieModel> results) {
        Log.d(TAG, " : PassedBy#Subhojit -> onPostExecute - START");

        super.onPostExecute(results);
        //String myStr = results.toString();
        loading.setVisibility(View.INVISIBLE);
        if(results != null){
            Log.d(TAG, " : PassedBy#Subhojit -> onPostExecute - IF-START");

            //ImageAdapter imageAdapter = new ImageAdapter(getApplicationContext(),R.id.gridViewId,results);
            SingleViewAdapter singleViewCreate = new SingleViewAdapter(getApplicationContext(),R.id.gridViewId,results);
            movieItems.setAdapter(singleViewCreate);
            Log.d(TAG, " : PassedBy#Subhojit -> onPostExecute - IF-> SetAdapter Done >>> SUCCESS");

        }else{
            showErrorMessage();
            Log.d(TAG, " : PassedBy#Subhojit -> onPostExecute - ELSE");

        }
        //delegate.processFinish(results);
       //textRawData.setText(results.toString());
        Log.d(TAG, " : PassedBy#Subhojit -> onPostExecute - END");
    }
}
}
