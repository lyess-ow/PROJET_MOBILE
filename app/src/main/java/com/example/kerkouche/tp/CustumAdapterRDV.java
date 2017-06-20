package com.example.kerkouche.tp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustumAdapterRDV extends BaseAdapter {

    private Context context;
    private List<RendezVous> listRDV = new ArrayList<RendezVous>();

    public CustumAdapterRDV(Context context, List<RendezVous> listRDV) {
        this.context = context;
        this.listRDV = listRDV;
    }

    @Override
    public int getCount() {
        return listRDV.size();
    }

    @Override
    public Object getItem(int position) {
        return listRDV.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = parent.inflate(context, R.layout.list_item_rdv,null);

        TextView annonce = (TextView) convertView.findViewById(R.id.annonce);
        TextView user = (TextView) convertView.findViewById(R.id.user);
        TextView date = (TextView) convertView.findViewById(R.id.date_rdv);
        TextView heure = (TextView) convertView.findViewById(R.id.heure_rdv);
        TextView lieu = (TextView) convertView.findViewById(R.id.lieuRDV);

        annonce.setText(annonce.getText() + listRDV.get(position).getAnnonce_titre());
        user.setText(user.getText() + listRDV.get(position).getNom_user_rdv() + " "
                + listRDV.get(position).getPrenom_user_rdv());
        date.setText(date.getText() + listRDV.get(position).getDate2_rdv());
        heure.setText(heure.getText() + listRDV.get(position).getTime_rdv());
        lieu.setText(lieu.getText() + listRDV.get(position).getLieu());

        return convertView;
    }
}