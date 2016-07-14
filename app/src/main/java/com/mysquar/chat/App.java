package com.mysquar.chat;

import android.app.Application;


import com.google.firebase.database.FirebaseDatabase;
import com.mysquar.chat.helpers.FirebaseHelper;

/**
 * Created by anhnguyen on 12/8/15.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppConfig.getInstance();
        FirebaseHelper.getInstance();

    }
}
