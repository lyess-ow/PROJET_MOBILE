package com.example.kerkouche.tp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

/**
 * Created by lyes on 19/03/17.
 */

public class CustumAdapterCommentaire extends BaseAdapter {

    private Context context;
    private List<Commentaire> listCommentaire = new ArrayList<Commentaire>();

    public CustumAdapterCommentaire(Context context, List<Commentaire> listCommentaire) {
        this.context = context;
        this.listCommentaire = listCommentaire;
    }

    public void clearData() {
        // clear the data
        listCommentaire.clear();
    }

    @Override
    public int getCount() {
        return listCommentaire.size();
    }

    @Override
    public Object getItem(int position) {
        return listCommentaire.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = parent.inflate(context, R.layout.list_item_commentaire,null);

        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView username = (TextView) convertView.findViewById(R.id.username);
        TextView commentaire = (TextView) convertView.findViewById(R.id.commentaire);
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBarCommentaire);
        username.setText(listCommentaire.get(position).getNom() + " " + listCommentaire.get(position).getPrenom());
        commentaire.setText(listCommentaire.get(position).getText());
        ratingBar.setRating(( Float.valueOf(listCommentaire.get(position).getNote())));
        date.setText(listCommentaire.get(position).getDate_comm() + " " + listCommentaire.get(position).getTime_comm());
        return convertView;
    }
}