package com.safa.fourquareapplication.service;

import android.app.Application;

import com.parse.Parse;

public class ParsaStartService extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
        .server("https://parseapi.back4app.com/")
        .applicationId("OOnlfVHrQvdfQezpZzuxEj5NHoymjTQCdWOVnvwS")
        .clientKey("mwFw3AaJ8pKLFsWfcK4UcJUY9wIwHVPEoI7dK0zl")
        .build());
    }
}
