package com.example.android.moviequeryapp;

import android.util.Log;

import com.example.android.moviequeryapp.models.MovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class ConvertJsonUnits {
    private static final String TAG = ConvertJsonUnits.class.getSimpleName();
    private static List<MovieModel> movieModelList = new ArrayList<>();

    public static List<MovieModel> getConvertedSimpleJsonData(String rawJsonData)
        throws JSONException {
        Log.d(TAG, " : PassedBy#Subhojit -> ConvertJsonUnits - START");



        //StringBuffer listBufferString = new StringBuffer();

        /*for(int index = 0; index < parentJA.length(); index++) {
            JSONObject finalJO = parentJA.getJSONObject(index);

            String movieName = finalJO.getString("title");
            String moviePosterPath = finalJO.getString("poster_path");
            String movieReleaseDate = finalJO.getString("release_date");
            String movieOverview = finalJO.getString("overview");
            listBufferString.append(movieName + " " + movieReleaseDate + "\n" +
                    moviePosterPath + " \n" + movieOverview + "\n\n\n");
                    =------======================================

                    String[] list = null;
		if (docObj.has(name)) {
			JSONArray json;
			try {
				json = docObj.getJSONArray(name);
				int lenFeatures = json.length();
				list = new String[lenFeatures];
				for (int j = 0; j < lenFeatures; j++) {
					String f = json.getString(j);
					list[j] = f;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
        }*/

        /////   JSONObject currently = forecast.getJSONObject("currently");    ///////


        //String[] lists = null;
       // List<MovieModel> movieModelList = null;
        if (rawJsonData != null){
            try{

                JSONObject parentJO = new JSONObject(rawJsonData);
                JSONArray parentJA = parentJO.getJSONArray("results");
                //int parentJAlength = parentJA.length();

                Log.d(TAG, " : PassedBy#Subhojit -> ConvertJsonUnits - BEFORE FOR LOOP");
                for(int i = 0; i < parentJA.length(); i++){
                    Log.d(TAG, " : PassedBy#Subhojit -> ConvertJsonUnits - INSIDE FOR LOOP");
                    //String textLine = parentJA.getString(i);

                    //JSONObject finalJO = parentJA.getJSONObject(Integer.parseInt(String.valueOf(i)));
                    JSONObject finalJO = parentJA.getJSONObject(i);   //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                    Log.d(TAG, " : PassedBy#Subhojit -> ConvertJsonUnits - FOR LOOP - " + i + " JSONobject");

                    MovieModel movieModel = new MovieModel();
                    Log.d(TAG, " : PassedBy#Subhojit -> ConvertJsonUnits - FOR LOOP - " + i + " movieModel");

                    movieModel.setTitle(finalJO.getString("title"));
                    Log.d(TAG, " : PassedBy#Subhojit -> ConvertJsonUnits - FOR LOOP - " + i + " setTitle");

                    movieModel.setPoster_path(finalJO.getString("poster_path"));
                    //movieModel.setRelease_date(finalJO.getString("release_date"));
                    //movieModel.setPoster_path(finalJO.getString("poster_path"));
                    //movieModel.setId(finalJO.getInt("genre_ids"));
                    //movieModel.setTitle(finalJO.getString("title"));

                    movieModelList.add(movieModel);
                    Log.d(TAG, " : PassedBy#Subhojit -> ConvertJsonUnits - END FOR LOOP");
                }
                return movieModelList;
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {

        }

        Log.d(TAG, " : PassedBy#Subhojit -> ConvertJsonUnits - FINISH");

        return movieModelList;
    }

}
