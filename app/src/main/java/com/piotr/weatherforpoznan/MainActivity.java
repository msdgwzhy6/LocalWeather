package com.piotr.weatherforpoznan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.crashlytics.android.Crashlytics;

import org.androidannotations.annotations.EActivity;

import io.fabric.sdk.android.Fabric;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    public void viewDetails(View v) {
        Intent Intent = new Intent(this, DetailsActivity.class);
        this.startActivity(Intent);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainActivityFragment())
                    .commit();
        }
    }

}
