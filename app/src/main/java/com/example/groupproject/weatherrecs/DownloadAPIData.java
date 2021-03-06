package com.example.groupproject.weatherrecs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.inputmethod.InputConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

/**
 * Created by Nichole on 4/16/2017.
 */

public class DownloadAPIData extends AsyncTask<String, Void, String> {


    //This connects to the Wunderground API's URL to grab current weather data upon opening the app.
    @Override
    protected String doInBackground(String... urls){

        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);

            int data = reader.read();

            while(data != -1){
                char current = (char) data;
                result += current;
                data = reader.read();
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //This parses the downloaded Wunderground JSON file for certain attributes, like current temperature or UV index, etc.
    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);

        //MainActivity.temperatureTextView.append("hello");

        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject weatherData = jsonObject.getJSONObject("current_observation");
            JSONObject cityData = weatherData.getJSONObject("display_location");


            //fetches string data from JSON
            String cityName = cityData.getString("city");
            MainActivity.cityTextView.append(cityName);



            String feelsLike = weatherData.getString("feelslike_f");
            MainActivity.temperatureTextView.append(feelsLike);



            /*String tempF = jsonObject.getString("feelslike_f");
            String UV = jsonObject.getString("UV");*/

            //fetches current weather icon
            String iconURL = weatherData.getString("icon_url");
            URL url = new URL(iconURL);
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());


            //Applies parsed data from JSON to UI elements
            MainActivity.iconImageView.setImageBitmap(bmp);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}