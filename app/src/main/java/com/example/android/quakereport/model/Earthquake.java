package com.example.android.quakereport.model;



public class Earthquake {

    private String mLocation;
    private long mTimeInMilliseconds;
    private double mMagnitude;
    /** Website URL of the earthquake */
    private String mUrl;



    public Earthquake() {
    }


    public Earthquake(double mMagnitude, String mLocation, long mTimeInMilliseconds, String mUrl) {
        this.mLocation = mLocation;
        this.mTimeInMilliseconds = mTimeInMilliseconds;
        this.mMagnitude = mMagnitude;
        this.mUrl = mUrl;
    }

    public long getmTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public void setmTimeInMilliseconds(long mTimeInMilliseconds) {
        this.mTimeInMilliseconds = mTimeInMilliseconds;
    }


    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
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
