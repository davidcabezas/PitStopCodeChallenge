package com.pitstop.test.global;

import android.util.Log;

import com.google.android.gms.maps.MapsInitializer;
import com.orm.SugarApp;

/**
 * Created by david on 27/2/18.
 */

public class MyApplication extends SugarApp {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        // Maps initialization in order to reduce first Map loading
        MapsInitializer.initialize(this);
        Log.d("MAPS INITIALIZED", "MAPS INITIALIZED");

    }

}
