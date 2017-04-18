package com.example.groupproject.weatherrecs;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    static TextView cityTextView;
    static TextView temperatureTextView;
    static ImageView iconImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityTextView = (TextView) findViewById(R.id.cityTextView);
        temperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
        iconImageView = (ImageView) findViewById(R.id.iconImageView);


        /**
         * This chunk gets user location via GPS app permissions.
         * TODO: Handle when the user denies current location by filling in the "if" statement below.
         */
        //LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //String provider = locationManager.getBestProvider(new Criteria(), false);


        /*debug shit
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        String pc = String.valueOf(permissionCheck);
        cityTextView.append(pc);
        //end debug shit*/

        /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }*/
        //Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        //This is messing things up!!
        //Double lat = location.getLatitude();
        //Double lng = location.getLongitude();*/

        //cityTextView.append("test");
        //temperatureTextView.append("test");



        /**
         * TODO: Use lat/lng data to generate an accurate weather JSON using Wunderground's URL
         * TODO: use different Wunderground JSON files for various uses. e.g. Use "geolookup" when grabbing the user's current city, or use "conditions" for current weather
         */
        DownloadAPIData task = new DownloadAPIData();
        task.execute("http://api.wunderground.com/api/43f3a903f5e333e9/conditions/q/LA/Baton_Rouge.json");

        //DownloadHourlyData hourlyTask = new DownloadHourlyData();
        //hourlyTask.execute("http://api.wunderground.com/api/43f3a903f5e333e9/hourly/q/LA/Baton_Rouge.json");

    }

}
