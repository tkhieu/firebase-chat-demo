package com.mysquar.chat;

import android.app.Application;
import android.content.Intent;
import android.support.multidex.MultiDexApplication;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.mysquar.chat.helpers.FirebaseHelper;
import com.mysquar.chat.service.FirebaseMessageService;
import com.mysquar.chat.service.InstanceIdService;

import static com.facebook.FacebookSdk.getApplicationContext;

public class App extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        AppConfig.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);


    }
}
