package com.mysquar.mychat;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by anhnguyen on 12/8/15.
 */
public class App extends Application {

    public static final String FIREBASE_URL = "https://mysquar-test.firebaseio.com/chat";

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseHelper.getInstance(getApplicationContext());
    }
}
