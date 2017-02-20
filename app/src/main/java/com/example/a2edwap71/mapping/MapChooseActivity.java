/**
 * Created by 2EDWAP71 on 06/02/2017.
 */
package com.example.a2edwap71.mapping;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;





public class MapChooseActivity extends Activity  implements View.OnClickListener{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mca);

        Button regular = (Button)findViewById(R.id.btnRegular);
        regular.setOnClickListener(this);
        Button cyclemap = (Button)findViewById(R.id.btnCyclemap);
        cyclemap.setOnClickListener(this);
    }
    public void onClick(View v)
    {
        Intent intent = new Intent();
        Bundle bundle=new Bundle();//data that passes between Activities
        boolean cyclemap=false;
        if (v.getId()==R.id.btnCyclemap)
        {
            cyclemap=true;
        }
        bundle.putBoolean("com.example.cyclemap",cyclemap);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }
}
