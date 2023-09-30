package com.uog.galleryswipe.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.uog.galleryswipe.R;
import com.uog.galleryswipe.activities.FullActivity;

public class FullSizeAdapter extends PagerAdapter{

    Context context;
    int[] images;
    LayoutInflater inflater;

    int nextPosition;
    int previousPosition;
    int current;


    public FullSizeAdapter(Context context, int[] images){
        this.context = context;
        this.images=images;
    }

    public FullSizeAdapter(){

    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        Log.i("pos", String.valueOf(position + "ad"));

        FullActivity fullActivity = new FullActivity();

        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.full_item,null);

        ImageView imageView = (ImageView) v.findViewById(R.id.img);
        Glide.with(context).load(images[position]).apply(new RequestOptions().centerInside())
                .into(imageView);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(inflater.getContext());

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("username", position);
        editor.commit();

        int pos = sharedPreferences.getInt("username", 0); //5, 4, 6, ..7


        previousPosition = current;
        current = pos;
        nextPosition = pos+1;

        if (previousPosition< nextPosition){
            current = nextPosition -1;
        }else {
            current = nextPosition +1;
        }

        Log.i("datasave", String.valueOf(current-1)+"current");


        if (position==15){
            editor.putInt("real", current);
        } else if (position==0) {
            editor.putInt("real", current-2);

        } else {
            editor.putInt("real", current-1);
        }

        editor.commit();


        ViewPager vp = (ViewPager) container;
        vp.addView(v,0);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

       ViewPager viewPager = (ViewPager) container;
       View v = (View) object;
       viewPager.removeView(v);
    }


}
