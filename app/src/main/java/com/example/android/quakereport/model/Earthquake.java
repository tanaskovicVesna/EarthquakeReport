package com.example.android.quakereport.model;

import java.util.Date;

public class Earthquake {

    private String mLocation;
    private String mDate;
    private double mMagnitude;

    public Earthquake(double mMagnitude, String mLocation,String mDate) {
        this.mLocation = mLocation;
        this.mDate = mDate;
        this.mMagnitude = mMagnitude;
    }

    public Earthquake() {
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public double getmMagnitude() {
        return mMagnitude;
    }

    public void setmMagnitude(double mMagnitude) {
        this.mMagnitude = mMagnitude;
    }



    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }
}
