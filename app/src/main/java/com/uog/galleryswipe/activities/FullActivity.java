package com.uog.galleryswipe.activities;

import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.uog.galleryswipe.R;
import com.uog.galleryswipe.adapters.FullSizeAdapter;

public class FullActivity extends Activity {

    Button btn_Left,btn_Right;
    ViewPager viewPager;
    int[] images;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full);

        if (savedInstanceState==null){
            Intent i = getIntent();
            images = i.getIntArrayExtra("IMAGES");
            position = i.getIntExtra("POSITION",0);

        }

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        FullSizeAdapter fullSizeAdapter = new FullSizeAdapter(this,images);
        viewPager.setAdapter(fullSizeAdapter);
        viewPager.setCurrentItem(position,true);


        btn_Left = findViewById(R.id.btn_Left);
        btn_Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                int pos = sharedPreferences.getInt("real", 0);

                viewPager.setCurrentItem(pos-1,true);

                Log.i("datasave", String.valueOf(pos)+"left click");

            }
        });

        btn_Right = findViewById(R.id.btn_Right);
        btn_Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                int pos = sharedPreferences.getInt("real", 0);

                viewPager.setCurrentItem(pos+1,true);

                Log.i("datasave", String.valueOf(pos)+"right click");

            }
        });

    }

}