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


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    /**
     * The part of the location string from the USGS service that we use to determine
     * whether or not there is a location offset present ("5km N of Cairo, Egypt").
     */
    private static final String LOCATION_SEPARATOR = " of ";

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


        //  location contains location offset and primary location
        String originalLocation = currentEarthquacke.getmLocation();
        String primaryLocation;
        String locationOffset;

        // Split the string into different parts (as an array of Strings)
        // based on the " of " text
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
           //set the default location offset to say "Near the".
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }
        //display location offset data
        TextView locationOffsetTextView = (TextView)listItemView.findViewById(R.id.location_offset);
        locationOffsetTextView.setText(locationOffset);


        //display primary location data
        TextView primaryLocationTextView = (TextView)listItemView.findViewById(R.id.primary_location);
        primaryLocationTextView.setText(primaryLocation);

        //create Date object
        Date dateObject = new Date(currentEarthquacke.getmTimeInMilliseconds());

        //display date
        TextView dateTextView = (TextView)listItemView.findViewById(R.id.date);
        //format date string
        String formattedDate = formatDate(dateObject);
        dateTextView.setText(formattedDate);

        //display time
        TextView timeTextView = (TextView)listItemView.findViewById(R.id.time);
        //format time string
        String formattedTime = formateTime(dateObject);
        timeTextView.setText(formattedTime);

        return listItemView;
    }

    /**
     * Helper method that formate date string from a Date object
     */
    private String formatDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Helper method that formate time string from a Date object
     */
    private String formateTime(Date dateObject){
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

}




