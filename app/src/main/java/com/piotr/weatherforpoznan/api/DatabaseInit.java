package com.piotr.weatherforpoznan.api;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;

/**
 * Created by Piotr on 06.10.2015.
 */
public class DatabaseInit extends Application {
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        //Notice this initialization code here
        ActiveAndroid.initialize(this);
    }
}
