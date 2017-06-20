package com.example.kerkouche.tp;

/**
 * Created by kerkouche on 26/03/17.
 */

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.kerkouche.tp.util.Consts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyes on 19/03/17.
 */

public class CustomAdapter extends BaseAdapter {


    private Context context;
    //private List<Annonce> listAnnonce = new ArrayList<Annonce>();
    private List<Annonce_s> listAnnonce = new ArrayList<Annonce_s>();
    public CustomAdapter(Context context, List<Annonce_s> listAnnonce) {
        this.context = context;
        this.listAnnonce = listAnnonce;
    }


    @Override
    public int getCount() {
        return listAnnonce.size();
    }

    @Override
    public Object getItem(int position) {
        return listAnnonce.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = parent.inflate(context, R.layout.list_item,null);
        ImageView imageList = (ImageView) convertView.findViewById(R.id.petiteImage);
        TextView textName = (TextView) convertView.findViewById(R.id.titre);
        TextView textPrice = (TextView) convertView.findViewById(R.id.prix);
        textName.setText(listAnnonce.get(position).getTitre());
        textPrice.setText(context.getString(R.string.string3) + listAnnonce.get(position).getPrix()+ context.getString(R.string.string4));
        Glide.with(context).load(Consts.file_host+listAnnonce.get(position).getUrl()).override(300,400)
                .into(imageList);
        return convertView;
    }
}
