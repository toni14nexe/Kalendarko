package com.example.mykalendarko;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<com.example.mykalendarko.Obavijest> arrayList;

    public MyAdapter(Context context, ArrayList<com.example.mykalendarko.Obavijest> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Object getItem(int position){
        return arrayList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.single_item,null);
        TextView t_id = (TextView) convertView.findViewById(R.id.id);
        TextView t_naziv = (TextView) convertView.findViewById(R.id.naziv);
        TextView t_detalji = (TextView) convertView.findViewById(R.id.detalji);
        TextView t_datum = (TextView) convertView.findViewById(R.id.datum);
        TextView t_ponoviti = (TextView) convertView.findViewById(R.id.ponoviti);

        com.example.mykalendarko.Obavijest obavijest = arrayList.get(position);

        t_id.setText(String.valueOf(obavijest.getID()));
        t_naziv.setText(obavijest.getName());
        t_detalji.setText("Napomena: " + obavijest.getDodatno());
        t_datum.setText(obavijest.getDay() + "." + obavijest.getMonth() + "." + obavijest.getYear() + ".");
        t_ponoviti.setText("Godišnje ponavljati: " + obavijest.getGodisnje());

        return convertView;
    }



    @Override
    public int getCount(){
        return this.arrayList.size();
    }
}
