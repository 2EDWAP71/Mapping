package com.example.a2edwap71.mapping;

/**
 * Created by 2edwap71 on 09/02/2017.
 */


import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;


public class Set_Location extends Activity  implements View.OnClickListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lat_and_lon);

        Button regular = (Button) findViewById(R.id.button);
        regular.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        Bundle bundle=new Bundle();//data that passes between Activities
        boolean button = false;

        if (view.getId() == R.id.button){

            button=true;
        }
        bundle.putBoolean("com.example.button",button);
        intent.putExtras(bundle);




        setResult(RESULT_OK,intent);
        finish();

    }
}
