package com.mysquar.mychat;

import android.app.Application;

import com.firebase.client.Firebase;
import com.mysquar.mychat.helpers.FirebaseHelper;

/**
 * Created by anhnguyen on 12/8/15.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppConfig.getInstance();
        Firebase.setAndroidContext(getApplicationContext());
        FirebaseHelper.getInstance();
    }
}
