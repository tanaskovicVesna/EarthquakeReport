package com.example.android.quakereport.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.android.quakereport.R;
import com.example.android.quakereport.model.Earthquake;


import java.text.DecimalFormat;
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

        //fotmat magnitude
        double magnitude = currentEarthquacke.getmMagnitude();
        String formattedMagnitude = formatMagnitude(magnitude);
        //display magnitude
        TextView magnitudeTextView = (TextView)listItemView.findViewById(R.id.magnitude);
        magnitudeTextView.setText(formattedMagnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(magnitude);
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


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
    /**
     * Helper method that formate magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    /**
     * Return the color for the magnitude circle based on the intensity of the earthquake.
     *
     * @param magnitude of the earthquake
     */
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        //means finding the closest integer less than the decimal value. The floor of the value 1.2 would be the integer 1
        int magnitudeFloor = (int) Math.floor(magnitude);

        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}




