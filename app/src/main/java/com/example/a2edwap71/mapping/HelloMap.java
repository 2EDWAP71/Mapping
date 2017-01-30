package com.example.a2edwap71.mapping;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class HelloMap extends Activity{
    //extends-where the file is from as well


    MapView mv;

    public void onCreate(Bundle savedInstanceState)
            //onCreate sets this code when first launched
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // this line tells OpenStreetMap about our app.
        // If you miss this out, you might get banned from OSM servers
        Configuration.getInstance().load
                (this, PreferenceManager.getDefaultSharedPreferences(this));

        mv = (MapView)findViewById(R.id.map1);
        //finds map1

        mv.setBuiltInZoomControls(true);
        //given zoom controls
        mv.getController().setZoom(14);
        //sets zoom level to 14, 14=street level
        mv.getController().setCenter(new GeoPoint(51.05,-0.72));
        //sets position of map


    }

}

