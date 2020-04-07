/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.quakereport.R;
import com.example.android.quakereport.adapter.EarthquakeAdapter;
import com.example.android.quakereport.model.Earthquake;
import com.example.android.quakereport.utils.QueryUtils;

import java.util.ArrayList;


public class EarthquakeActivity extends AppCompatActivity{

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    /** URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-05-02&minfelt=50&minmagnitude=5";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create an {@link AsyncTask} to perform the HTTP request to the given URL
        // on a background thread. When the result is received on the main UI thread,
        // then update the UI.
        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(USGS_REQUEST_URL);





    }

    /**
     * {@link AsyncTask} to perform the network request on a background thread, and then
     * update the UI with the first earthquake in the response.
     */
    private class  EarthquakeAsyncTask extends AsyncTask<String, Void, ArrayList<Earthquake>> {

        /**
         * This method is invoked (or called) on a background thread, so we can perform
         * long-running operations like making a network request.
         * <p>
         * It is NOT okay to update the UI from a background thread, so we just return an
         * {@link } object as the result.
         */
        @Override
        protected ArrayList<Earthquake>doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            ArrayList<Earthquake> earthquakes  = QueryUtils.fetchEarthquakeData(urls[0]);
            return earthquakes;
        }

        /**
         * This method is invoked on the main UI thread after the background work has been
         * completed.
         * <p>
         * It IS okay to modify the UI within this method. We take the {@link ArrayList<Earthquake>} object
         * (which was returned from the doInBackground() method) and update the views on the screen.
         */
        @Override
        protected void onPostExecute(ArrayList<Earthquake> result) {
            // If there is no result, do nothing.
            if (result == null) {
                return;
            }

            updateUi(result);
        }

    }

    /**
     * Update the UI with the given earthquake information.
     */
    private void updateUi( ArrayList<Earthquake> earthquakes) {
        // Create adapter that takes the list of earthquakes as input
        final EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Find the current earthquake that was clicked on
                Earthquake currentEarthquake = adapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getmUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

    }



}
