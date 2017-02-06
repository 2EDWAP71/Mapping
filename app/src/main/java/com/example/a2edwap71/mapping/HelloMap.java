package com.example.a2edwap71.mapping;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.content.Intent;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;

public class HelloMap extends Activity
    implements View.OnClickListener {


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

        mv = (MapView) findViewById(R.id.map1);
        //finds map1

        Button submitButton = (Button) findViewById(R.id.BtnCamera);
        submitButton.setOnClickListener(this);

        mv.setBuiltInZoomControls(true);
        //given zoom controls
        mv.getController().setZoom(14);
        //sets zoom level to 14, 14=street level
        mv.getController().setCenter(new GeoPoint(51.05, -0.72));
        //sets position of map

    }


    @Override
    public void onClick(View view) {
        EditText latitudeEditText = (EditText) findViewById(R.id.Latitude_editText1);
        double latitude = Double.parseDouble(latitudeEditText.getText().toString());

        EditText longitudeEditText = (EditText) findViewById(R.id.Longlitude_editText2);
        double longitude = Double.parseDouble(longitudeEditText.getText().toString());

        mv.getController().setCenter(new GeoPoint(latitude, longitude));
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();//menu inflater reads in the xml file. inflates and generates a menu object
        inflater.inflate(R.menu.menu_hello_map, menu);

        return true;//successful
    }//loads the menu from the xml file and generates a menu from it
    public boolean onOptionsItemSelected(MenuItem item)//when user selects a menu entry
    {
        if(item.getItemId() == R.id.choosemap)//gets the menu ID
        {

            Intent intent = new Intent(this,MapChooseActivity.class);
            startActivityForResult(intent,0);
            // react to the menu item being selected...
            //'System.exit(0);' -exits the application
            return true;
        }
        return false;
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent intent)
    {

        if(requestCode==0)
        {

            if (resultCode==RESULT_OK)
            {
                Bundle extras=intent.getExtras();
                boolean cyclemap = extras.getBoolean("com.example.cyclemap");
                if(cyclemap==true)
                {
                    mv.setTileSource(TileSourceFactory.CYCLEMAP);
                }
                else
                {
                    mv.getTileProvider().setTileSource(TileSourceFactory.MAPNIK);
                }
            }
        }
    }
}











