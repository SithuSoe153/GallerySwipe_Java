package com.uog.galleryswipe.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.slice.SliceItem;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Gallery;

import com.uog.galleryswipe.R;
import com.uog.galleryswipe.adapters.ImageAdapter;
import com.uog.galleryswipe.interfaces.RVclickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        final int[] images = new int[16];


        for (int i = 0; i < images.length; i++) {
            // Generate the resource ID based on the naming convention
            String imageName = "image" + (i + 1); // i + 1 to account for 1-based indexing
            int resourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());
            int resourceIdClone = getResources().getIdentifier("image16", "drawable", getPackageName());

            // Check if the resource exists before adding it to the array
            if (resourceId != 0) {
                images[i] = resourceId;
            } else {
                // Handle the case where the resource does not exist
                // You can log an error or take appropriate action here
                images[i] = resourceIdClone;
            }
        }

//        final RVclickListener listener = new RVclickListener() {
//            @Override
//            public void onClick(View view, int position) {



//                open full screen activity

//                Intent i = new Intent(getApplicationContext(),FullActivity.class);
//                i.putExtra("IMAGES", images);
//                i.putExtra("POSITION",position);
//                startActivity(i);


//            }
//        };

        final RVclickListener listener = (view, position) -> {

            Intent i = new Intent(getApplicationContext(),FullActivity.class);
            i.putExtra("IMAGES", images);
            i.putExtra("POSITION",position);
            startActivity(i);
        };

        ImageAdapter imageAdapter = new ImageAdapter(this,images,listener);
        recyclerView.setAdapter(imageAdapter);

    }
}