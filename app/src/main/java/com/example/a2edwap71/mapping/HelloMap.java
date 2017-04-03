package com.example.a2edwap71.mapping;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import android.widget.Toast;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.content.Intent;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;

public class HelloMap extends Activity
     {


    //extends-where the file is from as well



    MapView mv;
         ItemizedIconOverlay<OverlayItem> items;
         ItemizedIconOverlay.OnItemGestureListener<OverlayItem> markerGestureListener;


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



        mv.setBuiltInZoomControls(true);
        //given zoom controls
        mv.getController().setZoom(14);
        //sets zoom level to 14, 14=street level
        mv.getController().setCenter(new GeoPoint(51.05, -0.72));
        //sets position of map

        markerGestureListener = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>(){
            public boolean onItemLongPress (int i, OverlayItem item){
                Toast.makeText(HelloMap.this, item.getSnippet(), Toast.LENGTH_SHORT).show();
                return true;
            }
            public boolean onItemSingleTapUp (int i, OverlayItem item) {
                Toast.makeText(HelloMap.this, item.getSnippet(), Toast.LENGTH_SHORT).show();
                return true;
            }
        };

        items = new ItemizedIconOverlay<OverlayItem>(this, new ArrayList<OverlayItem>(), markerGestureListener);
        OverlayItem southampton = new OverlayItem("Southampton" , "City of Southampton" , new GeoPoint(50.9097,-1.4044 ));

        items.addItem(southampton);
        items.addItem(new OverlayItem("Bargate ", "Historical Site", new GeoPoint(50.9026,-1.4041)));
        mv.getOverlays().add(items);

        try {


            BufferedReader reader = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getAbsolutePath()+
                    "/poi.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] components = line.split(",");
                if (components.length==5){

                    OverlayItem currentPoi = new OverlayItem(components[0],components[2],
                            new GeoPoint( Double.parseDouble (components[4]) , Double.parseDouble(components[3]) ));
                            items.addItem(currentPoi);
                }
            }

        }
        catch(IOException e){
            System.out.println("ERROR:" + e);
        }

    }






    @Override

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();//menu inflater reads in the xml file. inflates and generates a menu object
        inflater.inflate(R.menu.menu_hello_map, menu);

        return true;//successful
    }//loads the menu from the xml file and generates a menu from it
    public boolean onOptionsItemSelected(MenuItem item)//when user selects a menu entry
    {
        if(item.getItemId() == R.id.listview)//gets the menu ID
        {

            Intent intent = new Intent(this,ChooseListActivity.class);
            startActivityForResult(intent,0);
            // react to the menu item being selected...
            //'System.exit(0);' -exits the application
            return true;
        }
        if (item.getItemId() == R.id.latandlon)//gets the menu ID
        {

            Intent intent = new Intent(this, Set_Location.class);
            startActivityForResult(intent, 1);
            return true;

        }
        if(item.getItemId() == R.id.preferences)
        {
            Intent intent = new Intent (this,MyPrefsActivity.class);
            startActivityForResult(intent,2);
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
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                Bundle extras1=intent.getExtras();
                double Latitude=extras1.getDouble("com.example.Latitude");
                double Longitude=extras1.getDouble("com.example.Longitude");

                mv.getController().setCenter(new GeoPoint(Latitude, Longitude));
            }
        }
    }
    public void onStart(){

        super.onStart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        double lat = Double.parseDouble (prefs.getString("lat", "50.9"));
        double lon = Double.parseDouble(prefs.getString("lon", "-1.4"));
        int zoom = Integer.parseInt(prefs.getString("zoom","14"));


        String mapTypeCode =prefs.getString("map","none");
        if (mapTypeCode.equals("RMV")){
            mv.setTileSource(TileSourceFactory.MAPNIK);
        }
        if (mapTypeCode.equals("CMV")){
            mv.getTileProvider().setTileSource(TileSourceFactory.CYCLEMAP);
        }
        mv.getController().setCenter(new GeoPoint(lat,lon));
        mv.getController().setZoom(zoom);




    }
        public void onDestroy(){
             super.onDestroy();
             SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
             SharedPreferences.Editor editor = prefs.edit();
             editor.putString("zoom",""+mv.getZoomLevel());
             editor.commit();
       }





}











