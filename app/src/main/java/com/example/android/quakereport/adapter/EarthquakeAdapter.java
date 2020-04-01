package com.example.android.quakereport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.quakereport.R;
import com.example.android.quakereport.model.Earthquake;


import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {


    public EarthquakeAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }



        Earthquake currentEarthquacke = getItem(position);

        //display magnitude
        TextView magnitudeTextView = (TextView)listItemView.findViewById(R.id.magnitude);
        magnitudeTextView.setText(Double.toString(currentEarthquacke.getmMagnitude()));

        //display location data
        TextView locationTextView = (TextView)listItemView.findViewById(R.id.location);
        locationTextView.setText(currentEarthquacke.getmLocation());

        //display date

        TextView dateTextView = (TextView)listItemView.findViewById(R.id.date);
        dateTextView.setText(currentEarthquacke.getmDate());

        return listItemView;
    }


}




