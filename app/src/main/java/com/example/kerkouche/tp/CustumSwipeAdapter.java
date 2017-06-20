package com.example.kerkouche.tp;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kerkouche.tp.util.*;

/**
 * Created by kerkouche on 26/03/17.
 */

public class CustumSwipeAdapter extends PagerAdapter {
    private String[] image_ressources;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustumSwipeAdapter(Context context,String[] image_ressources){

        this.context=context;
        this.image_ressources = (String[]) image_ressources.clone();
    }

    @Override
    public int getCount() {
        return image_ressources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View item_view =layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.image_view);
        // imageView.setImageResource(image_ressources[position]);
        Glide.with(context).load(Consts.file_host+image_ressources[position])
                .placeholder(R.drawable.appartement1).
                skipMemoryCache(true).into(imageView);
        container.addView(item_view);

        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
