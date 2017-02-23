package com.example.a2edwap71.mapping;

/**
 * Created by 2EDWAP71 on 23/02/2017.
 */

import android.preference.PreferenceActivity;
import android.os.Bundle;


public class MyPrefsActivity extends PreferenceActivity
{
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

    }


}