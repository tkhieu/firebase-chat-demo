package com.mysquar.chat;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.mysquar.chat.helpers.FirebaseHelper;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppConfig.getInstance();
        FirebaseHelper.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }
}
