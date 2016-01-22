package com.mysquar.mychat;

import android.content.Context;

import com.firebase.client.Firebase;

/**
 * Created by kimhieu on 1/22/16.
 */
public class FirebaseHelper {

    private static FirebaseHelper instance;
    private Firebase firebaseClient;

    public static FirebaseHelper getInstance(Context context){
        if(instance == null){
            instance = new FirebaseHelper(context);
        }
        return instance;
    }

    private FirebaseHelper(Context context){
        Firebase.setAndroidContext(context);
        firebaseClient = new Firebase(AppConfig.FIREBASE_URL);
    }

}
