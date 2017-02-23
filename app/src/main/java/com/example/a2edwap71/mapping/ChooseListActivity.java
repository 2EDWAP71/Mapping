package com.example.a2edwap71.mapping;

/**
 * Created by 2EDWAP71 on 20/02/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.ListView;;
import android.app.ListActivity;

public class ChooseListActivity extends ListActivity
{
    String[] names , details;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        names = new String[] { "Regular Map View","Cycle Map View" };
        details = new String[]{"Generates normal map view" , "Shows all possible cycle routes in the area"};
        MyAdapter adapter = new MyAdapter();
        setListAdapter(adapter);


    }


public void onListItemClick(ListView lv, View view, int index, long id)
        {   Intent intent = new Intent();
            Bundle bundle = new Bundle();

            boolean cyclemap=false;
            if (index==1)
            {
                cyclemap=true;
            }
            bundle.putBoolean("com.example.cyclemap",cyclemap);
            intent.putExtras(bundle);
            setResult(RESULT_OK,intent);
            finish();
        }
        // handle list item selection



    public class MyAdapter extends ArrayAdapter<String>
    {
        public MyAdapter()
        {
            // We have to use ExampleListActivity.this to refer to the outer class (the activity)
            super(ChooseListActivity.this, android.R.layout.simple_list_item_1, names);

        }

        public View getView(int index, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.list_activity, parent, false);
            TextView title = (TextView)view.findViewById(R.id.poi_name), detail =
                    (TextView)view.findViewById(R.id.poi_desc);
            title.setText(names[index]);
            detail.setText(details[index]);
            return view;
        }
    }

        }







